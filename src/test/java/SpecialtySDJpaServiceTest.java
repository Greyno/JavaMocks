import model.Speciality;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.SpecialityRepository;
import services.springdatajpa.SpecialtySDJpaService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpecialtySDJpaServiceTest {

    //Method 3 to create a mock: Use injection

    //We want to test SpecialtySDJpaService
    @Mock
    SpecialityRepository specialtyRepository;

    //Since SpecialtySDJpaService uses a SpecialityRepository, we can tell Mockito to create an instance
    // of SpecialtySDJpaService and inject a mock of SpecialityRepository into it

    //Create an instance of the Speciality service with the mock class injected into it
    @InjectMocks
    SpecialtySDJpaService service;

    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L); //when we add this line, verify logic will expect to be executed twice

        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeastOnce() {
        service.deleteById(1L);
        service.deleteById(1L); //when we add this line, verify logic will expect to be executed twice

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMostTwice() {
        service.deleteById(1L);
        service.deleteById(1L);
        //service.deleteById(1L); //if we add this line, verify logic will fail

        verify(specialtyRepository,atMost(2)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);

        verify(specialtyRepository,never()).deleteById(5L); //deletion wil never have the value 5L
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}
