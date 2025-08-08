package com.vvk.learnSP.learnRESTAPIs.Controller;

import com.vvk.learnSP.learnRESTAPIs.Services.StudentService;
import com.vvk.learnSP.learnRESTAPIs.dto.AddStudentDto;
import com.vvk.learnSP.learnRESTAPIs.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller -- used in traditional MVC applications
//@RestController   -- used in RESTful web services, combines @Controller and @ResponseBody

@RestController
@RequiredArgsConstructor
@RequestMapping("/students") // Base URL for all student-related APIs

public class StudentController {

//    private final StudentRepository studentRepository; //controller -> repository we need controller -> service -> repository
    private final StudentService studentService;

    /*
    // controller -> repository
    @GetMapping("/students")
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    // controller -> service -> repository
    // This is the preferred way to access the data in a RESTful application
    @GetMapping("/students")
    public List<StudentDto> getStudent() {
        return studentService.getAllStudents();
    }
     */

    /*
    // Apis creations for the path variables
    @GetMapping("/students/{id}")
    public String getStudentById(@PathVariable Long id) {
        return "Path Variable: " + id;
    }


    @GetMapping("/students/{id}/{name}")
    public String getStudentById(@PathVariable Long id, @PathVariable String name) {
    return "Path Variable: " + id + ", Name: " +name;
    }
     */

    //APIS with status codes

    //@GetMapping("/students")
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudent() {
      //return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //@GetMapping("/students/{id}")
    @GetMapping("/{id}")
    public  ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent
            (@RequestBody AddStudentDto addStudentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createNewStudent(addStudentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent
            (@PathVariable Long id, @RequestBody AddStudentDto addStudentDto ){
        return ResponseEntity.ok(studentService.updateStudent(id, addStudentDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> patchStudent
            (@PathVariable Long id, @RequestBody AddStudentDto addStudentDto) {
        return ResponseEntity.ok(studentService.patchStudent(id, addStudentDto));
    }
}


/* Notes */
/*
    @RequiredArgsConstructor
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    -- dont want to access the entity and repository access at the controller level
 */
