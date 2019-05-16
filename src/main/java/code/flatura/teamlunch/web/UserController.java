package code.flatura.teamlunch.web;

import code.flatura.teamlunch.service.UserService;
import code.flatura.teamlunch.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // TODO Conversion to DTO
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<UserTo> getById(@PathVariable(value = "id") Integer id) {
        return userService.getById(id)
                .map(u -> new ResponseEntity<>(new UserTo(u), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
