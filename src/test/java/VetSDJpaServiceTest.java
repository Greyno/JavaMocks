import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.VetRepository;
import services.springdatajpa.VetSDJpaService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    //Create an instance of the Vet service with the mock class injected into it
    @InjectMocks
    VetSDJpaService vetSDJpaService;

    @Test
    void testDeleteById(){
        vetSDJpaService.deleteById(1L);
        verify(vetRepository, times(1)).deleteById(1L);
    }

}
