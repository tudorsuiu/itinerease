package com.example.itinereasebackend.service;

import com.example.itinereasebackend.api.model.Itinerary;
import com.example.itinereasebackend.repository.ItineraryRepository;
import com.example.itinereasebackend.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItineraryService {
    @Autowired
    private final ItineraryRepository itineraryRepository;

    @Autowired
    private final LocationRepository locationRepository;

    public void create(Itinerary itinerary) {
        itineraryRepository.save(itinerary);
    }
    public List<Itinerary> getAll(){
        return itineraryRepository.findAll();
    }

    public Optional<Itinerary> getById(int itineraryId) {
        return itineraryRepository.findById(itineraryId);
    }

    public void update(int itineraryId, Itinerary updatedItinerary) {
        Optional<Itinerary> existingItinerary = itineraryRepository.findById(itineraryId);
        if (existingItinerary.isPresent()) {
            Itinerary itineraryToUpdate = existingItinerary.get();
            itineraryToUpdate.setDestination_location(updatedItinerary.getDestination_location());
            itineraryToUpdate.setTransport(updatedItinerary.getTransport());
            itineraryToUpdate.setUser(updatedItinerary.getUser());
            itineraryToUpdate.setAccommodation(updatedItinerary.getAccommodation());
            itineraryToUpdate.setDeparture_location(updatedItinerary.getDeparture_location());
            itineraryToUpdate.setName(updatedItinerary.getName());
            itineraryToUpdate.setDeparture_date(updatedItinerary.getDeparture_date());
            itineraryToUpdate.setArrival_date(updatedItinerary.getArrival_date());
            itineraryToUpdate.setBudget(updatedItinerary.getBudget());
            itineraryToUpdate.setPersons(updatedItinerary.getPersons());
            itineraryRepository.save(updatedItinerary);
        } else {
            // TODO Trebuie sa tratam cazul in care nu exista
            throw new RuntimeException("Itinerary not found with id: " + itineraryId);
        }
    }

    public void delete(int itineraryId) {
        // TODO trebuie sa tratam si cazul in care nu exista userul pe care vrem sa il stergem
        itineraryRepository.deleteById(itineraryId);
    }

    public List<Itinerary> getItinerariesByUserId(int userId) {
        return itineraryRepository.findByUser_Id(userId);
    }
    public Itinerary getItineraryByDetails(Itinerary itinerary) {
        Itinerary itineraryToBeFound;
        for(Itinerary itinerary1: itineraryRepository.findAll()){
            if(itinerary1 == itinerary){
                itineraryToBeFound = itinerary1;
                return itineraryToBeFound;
            }
        }
        throw new RuntimeException("Itinerary does not exist.");
    }
}
