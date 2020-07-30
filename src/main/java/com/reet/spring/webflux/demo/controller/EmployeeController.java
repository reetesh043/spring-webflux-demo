package com.reet.spring.webflux.demo.controller;

import com.reet.spring.webflux.demo.model.Employee;
import com.reet.spring.webflux.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = {"/create", "/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody Employee e) {

        employeeService.create(e);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
        Mono<Employee> e = employeeService.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Employee>>(e, status);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Flux<Employee> findByName(@PathVariable("name") String name) {

        return employeeService.findByName(name);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseBody
    public Flux<Employee> findAll() {
        Flux<Employee> e = employeeService.findAll();
        return e.delaySequence(Duration.ofSeconds(1)).log();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseBody
    public Flux<Employee> find() {
        Flux<Employee> e = employeeService.findAll();
        return e.log();
    }

    @RequestMapping(value = "/findAllEmp", method = RequestMethod.GET)
    @ResponseBody
    public Flux<Employee> findAllEmp() {
        Flux<Employee> e = employeeService.findAll();
        return e.log();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee e) {

        return employeeService.update(e);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {

        employeeService.delete(id).subscribe();
    }

    @RequestMapping(value = "/flux", method = RequestMethod.GET)
    @ResponseBody
    public Flux<Integer> flux() {
        return Flux.just(1, 2, 3, 4, 5, 6).log().delaySequence(Duration.ofSeconds(5));
    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void test() {
        for (int i = 1; i < 50; i++) {
            Employee emp = new Employee();
            emp.setId(i);
            emp.setName("user" + i);
            emp.setSalary(100 + i);
            employeeService.create(emp);
        }

    }

}
