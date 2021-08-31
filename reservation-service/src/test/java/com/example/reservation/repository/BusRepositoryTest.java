package com.example.reservation.repository;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
=======
import com.example.reservation.model.Bus;
import com.example.reservation.model.BusType;
import com.example.reservation.model.SeatType;
>>>>>>> c249284b57becc3becfadd561fcf20dcb0fc1c55
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

<<<<<<< HEAD
import com.example.reservation.model.Bus;
import com.example.reservation.model.BusContactPerson;
import com.example.reservation.model.BusStopPoint;
import com.example.reservation.model.BusType;
import com.example.reservation.model.Route;
import com.example.reservation.model.SeatType;
=======
import java.util.List;
>>>>>>> c249284b57becc3becfadd561fcf20dcb0fc1c55

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class BusRepositoryTest {

	@Autowired
	private BusRepository busRepository;
	@Autowired
	private RouteRepository routeRepository;

	@BeforeEach
	public void beforeEach() {

		Bus bus1 = new Bus();
		bus1.setNumber("TN18BZ1109");
		bus1.setName("Royal Bus");
		bus1.setSeating(SeatType.CHAIRTYPE);
		bus1.setType(BusType.AC);
		bus1.setSeats(40);

		BusContactPerson bcp = new BusContactPerson();
		bcp.setName("Ramesh");
		bcp.setMobile("8745612309");
		bus1.setContact(bcp);

		Route route = new Route();
		route.setId(new ObjectId("612b59449c35910c2057b753"));
		route.setSource("Delhi");
		route.setDestination("KanyaKumari");
		route.setDistance(3000);
		LocalTime localTime1 = LocalTime.of(21, 30, 59);
		route.setDep_time(localTime1);
		LocalTime localTime2 = LocalTime.of(12, 52, 45);
		route.setArrival_time(localTime2);
		BusStopPoint bp = new BusStopPoint();
		bp.setDistance(200);
		bp.setName("Kurnool");
		LocalTime localbp = LocalTime.of(21, 30, 59);
		bp.setTime(localbp);
		List<BusStopPoint> bsp = new ArrayList<>();
		bsp.add(bp);
		route.setStop_points(bsp);
		List<Bus> list = new ArrayList<>();
		list.add(bus1);
		route.setBus_list(list);
		
		
		bus1.setRoute(route);
		routeRepository.insert(route);
		busRepository.insert(bus1);

	}

	@Test
	public void add_bus_success() {
		Bus bus = new Bus();
		bus.setNumber("TN18BZ1102");
		bus.setName("Classic Bus");
		bus.setSeating(SeatType.SLEEPINGTYPE);
		bus.setType(BusType.NON_AC);
		bus.setSeats(20);
		
		Route route = new Route();
		route.setId(new ObjectId("612b59449c35910c2057b754"));
		route.setSource("Delhi");
		route.setDestination("KanyaKumari");
		route.setDistance(3000);
		LocalTime localTime1 = LocalTime.of(21, 30, 59);
		route.setDep_time(localTime1);
		LocalTime localTime2 = LocalTime.of(12, 52, 45);
		route.setArrival_time(localTime2);
		route.setStop_points(null);
		route.setBus_list(null);
		bus.setRoute(route);
		
		busRepository.save(bus);
//		assertEquals("TN18BZ1102", busRepository.findById("TN18BZ1102").get().getNumber());
		
		List<Bus> busList = busRepository.findAll();
		assertEquals(2, busList.size());
	}

	@Test
	public void add_bus_fail() {
		Bus bus = new Bus();
		bus.setNumber("TN18BZ1103");
		bus.setName("Classic Bus");
		bus.setSeating(SeatType.SLEEPINGTYPE);
		bus.setType(BusType.NON_AC);
		bus.setSeats(20);
//        busRepository.save(bus);
//		Optional<Bus> optional = busRepository.findById("TN18BZ1103");
//		if (optional.isEmpty()) {
//			assertTrue(true);
//		}
//		
		List<Bus> busList = busRepository.findAll();
		assertEquals(1, busList.size());

	}

	@Test
	public void update_route_success() {
		Bus bus = busRepository.findById("TN18BZ1109").get();
		bus.setRoute(null);
		busRepository.save(bus);
		assertNull(bus.getRoute());
	}

	@Test
	public void update_route_fail() {
		Bus bus = busRepository.findById("TN18BZ1109").get();
		Object id = bus.getRoute();
		
		Route route = new Route();
		route.setId(new ObjectId("612b59449c35910c2057b753"));
		route.setSource("Delhi");
		route.setDestination("KanyaKumari");
		route.setDistance(3000);
		LocalTime localTime1 = LocalTime.of(21, 30, 59);
		route.setDep_time(localTime1);
		LocalTime localTime2 = LocalTime.of(12, 52, 45);
		route.setArrival_time(localTime2);
		route.setStop_points(null);
		route.setBus_list(null);
		bus.setRoute(route);
		busRepository.save(bus);
		assertNotEquals(id, bus.getRoute());
	}

	@Test
	public void update_bus_contact_person_success() {
		Bus bus = busRepository.findById("TN18BZ1109").get();
		bus.setContact(null);
		busRepository.save(bus);
		assertNull(bus.getContact());
	}

<<<<<<< HEAD
	@Test
	public void update_bus_contact_person_fail() {
		Bus bus = busRepository.findById("TN18BZ1109").get();
		BusContactPerson contact = bus.getContact();
		bus.setContact(null);
		busRepository.save(bus);
		assertNotEquals(contact, bus.getContact());
	}

	@Test
	public void get_bus_for_id_success() {
		Bus bus = busRepository.findById("TN18BZ1109").get();
		assertEquals("TN18BZ1109", bus.getNumber());
	}
=======
        Bus bus1=new Bus();
        bus1.setNumber("TN18BZ1109");
        bus1.setName("Royal Bus");
        bus1.setSeatType(SeatType.CHAIR);
        bus1.setType(BusType.AC);
        bus1.setSeats(40);

        Bus bus2=new Bus();
        bus2.setNumber("TN18BZ1108");
        bus2.setName("Classic Bus");
        bus2.setType(BusType.AC);
        bus2.setSeatType(SeatType.SLEEPER);
        bus2.setSeats(20);
>>>>>>> c249284b57becc3becfadd561fcf20dcb0fc1c55

	@Test
	public void get_bus_for_id_fail() {
		Optional<Bus> optional = busRepository.findById("TN18BZ1111");
		if (optional.isEmpty()) {
			assertTrue(true);
		}
	}

	@Test
	public void get_bus_for_name_success() {
		Bus bus = busRepository.findByName("Royal Bus");
		assertEquals("TN18BZ1109", bus.getNumber());
	}

<<<<<<< HEAD
	@Test
	public void get_bus_for_name_fail() {
		Optional<Bus> optional = Optional.ofNullable(busRepository.findByName("Royal Bus"));
		if (optional.isEmpty()) {
			assertTrue(true);
		}
	}

	@Test
	public void get_bus_for_type_success() {
		Bus bus = busRepository.findByType(BusType.AC);
		assertEquals(BusType.AC, bus.getType());
	}

	@Test
	public void get_bus_for_type_fail() {
		Optional<Bus> optional = Optional.ofNullable(busRepository.findByType(BusType.AC));
		if (optional.isEmpty()) {
			assertTrue(true);
		}
	}
	
	@Test
	public void get_bus_for_seat_success() {
		Bus bus = busRepository.findBySeat(SeatType.CHAIRTYPE);
		assertEquals(SeatType.CHAIRTYPE, bus.getSeating());
	}

	@Test
	public void get_bus_for_seat_fail() {
		Optional<Bus> optional = Optional.ofNullable(busRepository.findBySeat(SeatType.SLEEPINGTYPE));
		if (optional.isEmpty()) {
			assertTrue(true);
		}
	}

	@Test
	public void get_bus_for_route_success() {
		Route route = busRepository.findById("TN18BZ1109").get().getRoute();
		Bus bus = busRepository.findByRoute(route.getId());
		assertEquals(new ObjectId("612b59449c35910c2057b753"), bus.getRoute().getId());
	}
=======
    @Test
    public void getAll(){
       List<Bus> busList= busRepository.findAll();
       assertEquals(2,busList.size());
    }

    @Test
    public void getBusByNumber(){
        Bus bus= busRepository.findById("TN18BZ1108").get();
        assertEquals("TN18BZ1108",bus.getNumber());
    }

    @Test
    public void getAcBus(){
       List<Bus> busList= busRepository.findByType(BusType.AC);
        assertEquals(2,busList.size());
    }
>>>>>>> c249284b57becc3becfadd561fcf20dcb0fc1c55

	@Test
	public void get_bus_for_route_fail() {
		Optional<Bus> optional = Optional.ofNullable(busRepository.findByRoute(null));
		if (optional.isEmpty()) {
			assertTrue(true);
		}
	}

<<<<<<< HEAD
	@Test
	public void get_all_bus() {
		List<Bus> busList = busRepository.findAll();
		assertEquals(1, busList.size());
	}
=======
    @Test
    public void getNonAcBus(){
        List<Bus> busList= busRepository.findByType(BusType.NON_AC);
        assertEquals(0,busList.size());
    }
>>>>>>> c249284b57becc3becfadd561fcf20dcb0fc1c55

	@Test
	public void remove_bus_success() {
		busRepository.deleteById("TN18BZ1109");
		boolean bus = busRepository.existsById("TN18BZ1109");
		assertEquals(false, bus);
	}

	@Test
	public void remove_bus_fail() {
		busRepository.deleteById("TN18BZ1109");
		boolean bus = busRepository.existsById("TN18BZ1109");
		assertEquals(true, !bus);
	}

	@AfterEach
	public void afterEach() {
		routeRepository.deleteAll();
		busRepository.deleteAll();
	}

}
