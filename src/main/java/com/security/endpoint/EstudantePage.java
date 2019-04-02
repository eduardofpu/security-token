package com.security.endpoint;

import com.security.error.ResourceNotFoundException;
import com.security.repository.StudentRepositoryPagination;
import com.security.model.StudenteEnty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("v1")
public class EstudantePage{

    private final StudentRepositoryPagination studentDao;

    @Autowired
    public EstudantePage(StudentRepositoryPagination studentDao){
        this.studentDao = studentDao;

    }
    //localhost:8080/estudantepage?sort=name,asc localhost:8080/estudantepage?sort=name,desc
    @GetMapping(path = "protected/estudents") //localhost:8080/estudantepage?page=1 localhost:8080/estudantepage?page=0&size=4
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(studentDao.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "protected/estudents/{id}")
    public ResponseEntity<?> getStudentId(@PathVariable("id") Long id, Authentication authentication) {
        System.out.println(authentication);
        verifyIfStudentExists(id);
        StudenteEnty studente =  studentDao.findOne(id);
        return new ResponseEntity<>(studente,HttpStatus.OK);
    }

    @GetMapping(path = "protected/estudents/findByName/{name}")
    public ResponseEntity<?> findBStudentByName(@PathVariable String name){
        return new ResponseEntity<>(studentDao.findByNameIgnoreCaseContaining(name),HttpStatus.OK);
    }

    @PostMapping(path = "admin/estudents")
    @Transactional(rollbackFor = Exception.class)// So faz rooback quando nao tem execepiton do tipo runtime
    public ResponseEntity<?> save(@Valid @RequestBody StudenteEnty studente) {

        return  new ResponseEntity<>(studentDao.save(studente),HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/estudents/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyIfStudentExists(id);
        studentDao.delete(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "protected/estudents/deleteall")
    public  ResponseEntity<?> deleteAll(){
        studentDao.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "admin/estudents")
    public ResponseEntity<?> update(@RequestBody StudenteEnty studente) {
        verifyIfStudentExists(studente.getId());
        studentDao.save(studente);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "protected/estudents/totalelementos")
    public ResponseEntity<?> totalElementos(){

        Long contador = studentDao.count();
        String cont = String.valueOf("Total de elementos no banco de dados = "+contador);
        System.out.println(cont);
        return new ResponseEntity<>(cont,HttpStatus.OK);
    }

    @GetMapping(path = "protected/estudents/contador")
    public ResponseEntity<?> contador(){
        int cont = 0;
        cont = getCont(cont);
        System.out.println(cont);
       // String mostrarTotal = String.valueOf("Total de nomes escolhido = "+cont);
        String mostrarTotal = String.format("Total de nomes escolhido  = %s ",cont);
        return new ResponseEntity<>(mostrarTotal,HttpStatus.OK);
    }

    private int getCont(int cont) {
        List<StudenteEnty> nome = studentDao.findByNameIgnoreCaseContaining("goku");
        for(StudenteEnty s : nome){
            if(s.getName().equals("goku"))
                cont++;
        }
        return cont;
    }

    private void verifyIfStudentExists(Long id){
        if(studentDao.findOne(id) == null){

            throw new ResourceNotFoundException("Student not found for ID: "+id);
        }
    }


}
