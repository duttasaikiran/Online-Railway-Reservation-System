package com.RailwayReservationAdminManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RailwayReservationAdminManagement.model.TrainDetails;
import com.RailwayReservationAdminManagement.service.AdminServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminServiceImpl adminServiceImpl;
    
	
	/**To Search All Train Details For Admin**/
	@GetMapping("/all")
	@ApiOperation(value = "Get all train details")
	public List<TrainDetails> getAllDetails() {
		return adminServiceImpl.getAllDetails();
	}
	/**To Search Trains Using TrainNo For Admin**/
	@GetMapping("/{trainNo}")
	@ApiOperation(value = "Get all train details by Train Number")
	public TrainDetails getDetailsByTrainNo(@RequestParam Integer trainNo) {
		return adminServiceImpl.getDetailsByTrainNo(trainNo);
	}

	@PostMapping("/addtrain")
	@ApiOperation(value = "Add new train details to train database")
	public String addTrainDetails(@Valid @RequestBody TrainDetails trainDetails) {
		adminServiceImpl.addTrainDetails(trainDetails);
		return ("Added Train Details With Train Number - " + trainDetails.getTrainNo() + " Successfully..!!");
	}
	/**To Update Trains Using TrainNo & TrainDetails For Admin**/
	@PutMapping("/update/{trainNo}")
	@ApiOperation(value = "Update train details in train database by Train Number")
	public String updateTrainDetails(@PathVariable Integer trainNo,
			@Valid @RequestBody TrainDetails trainDetails) {
		adminServiceImpl.updateTrainDetails(trainNo, trainDetails);
		return ("Updated Train Details With Train No -" + trainDetails.getTrainNo() + " Successfully..!!");
	}
    
	/**To Delete Trains Using TrainNo For Admin**/
	@DeleteMapping("/delete/{trainNo}")
	@ApiOperation(value = "Delete train details in train database by Train Number")
	public String deleteTrainDetails(@PathVariable Integer trainNo) {
		adminServiceImpl.deleteTrainDetails(trainNo);
		return ("Removed Train Details With Train No -" + trainNo);
	}

	/**Update The No Of Seats Based On The No Of Passengers That Booked Ticket**/
	@GetMapping("/updateSeats/{trainNo}/{noOfPassengers}")
	public void updateSeats(@PathVariable int trainNo,@PathVariable int noOfPassengers)
	{
		TrainDetails currentTrain=adminServiceImpl.getDetailsByTrainNo(trainNo);
		int initialSeats=currentTrain.getNoOfSeats();
		int finalSeats=initialSeats-noOfPassengers;
		currentTrain.setNoOfSeats(finalSeats);
		adminServiceImpl.updateTrainDetails(trainNo, currentTrain);
	}

}
