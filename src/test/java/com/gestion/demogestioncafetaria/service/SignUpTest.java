package com.gestion.demogestioncafetaria.service;

import com.gestion.demogestioncafetaria.entity.User;
import com.gestion.demogestioncafetaria.exception.ResourceAlreadyExistException;
import com.gestion.demogestioncafetaria.repository.UserRepository;
import com.gestion.demogestioncafetaria.resource.user.SignUpRequest;
import com.gestion.demogestioncafetaria.service.user.SignUp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private SignUp signUp;

    @Test
    void shouldSignUpSuccessfully() {
        var request = getSIgnupRequest();
        when(this.userRepository.existsByEmail(request.email())).thenReturn(false);

        signUp.execute(request);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldSignUpWithEmailAlresdyExistInDatabase() {
        var request = getSIgnupRequest();
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(
                ResourceAlreadyExistException.class,
                () -> signUp.execute(request)
        );
    }

    private SignUpRequest getSIgnupRequest() {
        return new SignUpRequest("jean", "6584712", "jean@exemple.cm", "pass123");
    }
}
