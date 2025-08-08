package com.vvk.learnSP.learnRESTAPIs.Services;

import com.vvk.learnSP.learnRESTAPIs.dto.AddStudentDto;
import com.vvk.learnSP.learnRESTAPIs.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentDto addStudentDto);

    void deleteStudent(Long id);

    StudentDto updateStudent(Long id, AddStudentDto addStudentDto);

    StudentDto patchStudent(Long id, AddStudentDto addStudentDto);
}
