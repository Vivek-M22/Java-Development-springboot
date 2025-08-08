package com.vvk.learnSP.learnRESTAPIs.Services.Imple;

import com.vvk.learnSP.learnRESTAPIs.Entity.Student;
import com.vvk.learnSP.learnRESTAPIs.Repository.StudentRepository;
import com.vvk.learnSP.learnRESTAPIs.Services.StudentService;
import com.vvk.learnSP.learnRESTAPIs.dto.AddStudentDto;
import com.vvk.learnSP.learnRESTAPIs.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
 public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students
                .stream()
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
                .toList();
        return studentDtos;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
                return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentDto addStudentDto) {
        Student newStudent = modelMapper.map(addStudentDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentDto addStudentDto) {
        Student student = studentRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
        modelMapper.map(addStudentDto, student);

        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto patchStudent(Long id, AddStudentDto addStudentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));

        if (addStudentDto.getName() != null) {
            student.setName(addStudentDto.getName());
        }
        if (addStudentDto.getEmail() != null) {
            student.setEmail(addStudentDto.getEmail());
        }

        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }
}
