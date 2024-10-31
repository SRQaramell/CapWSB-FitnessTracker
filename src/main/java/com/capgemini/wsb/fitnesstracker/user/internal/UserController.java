package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final UserSimpleMapper userSimpleMapper;
    private final UserEmailSimpleMapper userEmailSimpleMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple()
    {
        return userService.findAllUsers().stream().map(userSimpleMapper::toSimpleDto).toList();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId)
    {
        return userService.getUser(userId).map(userMapper::toDto).orElseThrow(() -> new IllegalArgumentException("User " + userId + " not in database"));
    }

    @GetMapping("/email")
    public List<UserEmailSimpleDto> getUserByEmail(@RequestParam String email)
    {
        return userService.getUserByEmailCaseInsensitive(email).stream().map(userEmailSimpleMapper::toEmailSimpleDto).toList();
    }

    @GetMapping("/older/{time}")
    public List<UserDto> getUserOlderThanAge(@PathVariable LocalDate date)
    {
        return userService.getUsersOlderThanAge(date).stream().map(userMapper::toDto).toList();
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException
    {
        try
        {
            User user = userMapper.toEntity(userDto);
            userService.createUser(user);
        } catch (Exception e)
        {
            throw new IllegalArgumentException("Cannot add user with email: " + userDto.email() + " Error: " + e.getMessage());
        }

        return null;
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userID, @RequestBody UserDto userDto)
    {
        try
        {
            User retrievedUser = userService.getUser(userID).orElseThrow(() -> new IllegalArgumentException("User " + userID + " not found"));
            User updateUser = userMapper.toUpdateEntity(userDto, retrievedUser);

            return userService.updateUser(updateUser);
        } catch (Exception e)
        {
            throw new IllegalArgumentException("Cannot update " + userID + " Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userID)
    {
        try
        {
            userService.deleteUser(userID);
        } catch (Exception e)
        {
            throw new IllegalArgumentException("Cannot delete user " + userID + " Error: " + e.getMessage());
        }
    }

}