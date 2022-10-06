package jact.lagaltproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.dtos.freelancer.FreelancerDTO;
import jact.lagaltproject.services.freelancer.FreelancerService;
import jact.lagaltproject.util.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/freelancers")
public class FreelanceController {


    private final FreelancerService freelancerService;

    /*
     *  Abase URL is defined and the relevant service is injected.
     *
     * */

    public FreelanceController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    /*
     * The getAll methods gets all the freelancers in the table Freelancer.
     * */
    @Operation(summary = "Get all freelancers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FreelancerDTO.class))) })
    })
    @GetMapping // GET: localhost:8081/api/v1/freelancers
    public ResponseEntity<Collection<Freelancer>> getAll() {
        return ResponseEntity.ok(freelancerService.findAll());
    }

    /*
     * The findById methods gets the freelancers with the provided id.
     * */
    @Operation(summary = "Gets a character by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Character.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Character with supplied ID does not exist",
                    content = @Content)
    })
    @GetMapping("{id}") // GET: localhost:8081/api/v1/freelancers/1
    public ResponseEntity<Freelancer> findById(@PathVariable long id) {
        return ResponseEntity.ok(freelancerService.findById(id));
    }

    /*
     * The findByName method searches for the freelancers with the provided name.
     * */
    @Operation(summary = "Searches after freelancers by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Character.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Character with supplied ID does not exist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    @GetMapping("search") // GET: localhost:8081/api/v1/freelancers/search?name=Thor
    public ResponseEntity<Collection<Freelancer>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(freelancerService.findAllByName(name));
    }

    @Operation(summary = "Adds a freelancer")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Freelancer successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Freelancer with supplied ID not found ",
                    content = @Content)
    })
    @PostMapping // POST: localhost:8081/api/v1/freelancers
    public ResponseEntity add(@RequestBody Freelancer freelancer) {
        Freelancer aFreelancer = freelancerService.add(freelancer);
        URI location = URI.create("freelancers/" + aFreelancer.getId());
        return ResponseEntity.created(location).build();
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a freelancer")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Freelancer successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Freelancer with supplied ID not found ",
                    content = @Content)
    })
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/freelancers/1
    public ResponseEntity update(@RequestBody Freelancer aFreelancer, @PathVariable int id) {
        // Validates if body is correct
        if(id != aFreelancer.getId())
            return ResponseEntity.badRequest().build();
        freelancerService.update(aFreelancer);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a freelancer")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "410",
                    description = "Freelancer successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Freelancer with supplied ID not found ",
                    content = @Content)
    })
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/freelancers/1
    public ResponseEntity delete(@PathVariable long id) {
        freelancerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
