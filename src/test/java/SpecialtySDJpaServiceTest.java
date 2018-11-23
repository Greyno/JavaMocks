import model.Speciality;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.SpecialityRepository;
import services.springdatajpa.SpecialtySDJpaService;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //Will get null in test results otherwise
public class SpecialtySDJpaServiceTest {

    //Method 3 to create a mock: Use injection

    //We want to test SpecialtySDJpaService
    //Since SpecialtySDJpaService uses a SpecialityRepository, we can tell Mockito to create a mock of
    //SpecialityRepository
    @Mock
    SpecialityRepository specialtyRepository;

    //Now we can tell Mockito to create an instance of SpecialtySDJpaService and inject a mock of
    // SpecialityRepository into it
    //Create an instance of the SpecialitySDJpService and inject the mock class into it
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

    //Returning data from mocks
    @Test
    void findByIdTest() {
        //Since findById returns a Speciality, create an object our mock will return
        Speciality speciality = new Speciality();

        //Mock the response from the Speciality object when the findById method is called
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        //Make the method call under the class that is being tested (i.e. SpecialitySDJpaService) (invoke the service)
        Speciality foundSpeciality = service.findById(1L);
        assert(foundSpeciality.equals(speciality));
        //assertThat(foundSpeciality).isNotNull();

        //use Argument Matchers to verify any Long value entered
        verify(specialtyRepository).findById(anyLong()); //verify mock implementation was called
    }

    @Test
    void testDeleteByObject(){
        Speciality speciality = new Speciality();
        service.delete(speciality);

        //verify that the delete method is called with any Speciality object
        verify(specialtyRepository).delete(any(Speciality.class));

    }
}
