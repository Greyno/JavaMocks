package services.springdatajpa;

import model.Visit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.VisitRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //Will get null in test results otherwise
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void testFindAll() {
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();

        visits.add(visit);

        //Mock the response from the Visit object when the findById method is called
        when(visitRepository.findAll()).thenReturn(visits);

        //Make the method call under the class that is being tested (i.e. VisitSDJpaService) (invoke the service)
        Set<Visit> visitsFound = service.findAll();

        verify(visitRepository).findAll();

        assert(visitsFound.equals(visits));
        assertEquals(visitsFound.size(), 1);
    }

    @Test
    void testFindById() {
        Visit visit = new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);

        verify(visitRepository).findById(anyLong());
        assert(foundVisit.equals(visit));
        assertNotNull(foundVisit);
    }

    @Test
    void testSave() {
        Visit visit = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        Visit visitSaved = service.save(visit);

        verify(visitRepository).save(any(Visit.class));
        assert(visit.equals(visitSaved));
        assertNotNull(visitSaved);

    }

    @Test
    void testDelete() {
        Visit visit = new Visit();
        service.delete(visit); //no return value, so don't need when()

        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }
}