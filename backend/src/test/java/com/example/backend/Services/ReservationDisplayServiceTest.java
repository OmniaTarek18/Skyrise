package com.example.backend.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import com.example.backend.HelperData;
import com.example.backend.DTOMappers.ReservationMapper;
import com.example.backend.DTOs.ReservationDTO;
import com.example.backend.DTOs.ReservationFilterCriteria;
import com.example.backend.Entities.Reservation;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ReservationDisplayServiceTest {

        @Mock
        private ReservationRepository reservationRepository;

        @Mock
        private UserRepository userRepository;

        @Mock
        private FlightRepository flightRepository;

        @Mock
        private AirportRepository airportRepository;

        @Mock
        private FlightLegRepository flightLegRepository;

        @InjectMocks
        private ReservationDisplayService reservationDisplayService;

        private final List<Reservation> actual = new ArrayList<>();

        @BeforeEach
        public void setup() {
                HelperData data = new HelperData(airportRepository, flightRepository, flightLegRepository,
                                userRepository,
                                reservationRepository);
                data.dataGeneration();
                airportRepository = data.getAirportRepository();
                flightRepository = data.getFlightRepository();
                flightLegRepository = data.getFlightLegRepository();
                userRepository = data.getUserRepository();
                reservationRepository = data.getReservationRepository();
        }

        @Test
        @DisplayName("Test 1: get reserved flights by user id returns correct reserved flights")
        @Rollback(true)
        void testGetReservationByUserIdCorrectness() {
                Page<Reservation> actualPage = new PageImpl<>(actual);
                Page<ReservationDTO> actualPageDTO = actualPage.map(ReservationMapper::toDTO);

                given(reservationRepository.findByUserId(1, PageRequest.of(0, 10))).willReturn(actualPage);

                var result = reservationDisplayService.getReservationByUserId(1, 0);

                assertNotNull(result);
                assertEquals(1, result.content().size());
                assertThat(actualPageDTO.getContent()).usingRecursiveFieldByFieldElementComparator()
                                .containsExactlyElementsOf(result.content());
        }

        @Test
        @DisplayName("Test 2: get reserved flights by user id and filter based on some criteria")
        @Rollback(true)
        void testFilterReservedCorrectnessAllFields() {
                ReservationFilterCriteria filterDTO = new ReservationFilterCriteria(
                                1,
                                "New York, USA",
                                "Los Angeles, USA",
                                null,
                                null,
                                null,
                                null,
                                null);

                List<ReservationDTO> expected = List.of(
                                new ReservationDTO(1, 1, "New York, USA", "Los Angeles, USA",
                                                LocalDate.parse("2024-10-12"), LocalTime.of(10, 30),
                                                LocalDate.parse("2024-09-12"), LocalTime.of(12, 30),
                                                SeatClass.ECONOMY, 2));

                var result = reservationDisplayService.filterReserved(filterDTO, 0);

                assertNotNull(result);
                assertEquals(1, result.content().size());
                assertThat(result.content()).usingRecursiveFieldByFieldElementComparator()
                                .containsExactlyElementsOf(expected);
        }
}
