package jact.lagaltproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jact.lagaltproject.enums.Role;
import jact.lagaltproject.mappers.ProjectMapper;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.ProjectFreelancer;
import jact.lagaltproject.models.ProjectFreelancerKey;
import jact.lagaltproject.models.dtos.project.ProjectDTO;
import jact.lagaltproject.services.freelancer.FreelancerService;
import jact.lagaltproject.services.project.ProjectService;
import jact.lagaltproject.services.projectFreelancer.ProjectFreelancerService;
import jact.lagaltproject.util.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectFreelancerService pfService;
    private final FreelancerService freelancerService;
    private final ProjectMapper projectMapper;

    /*
     *  Abase URL is defined and the relevant service is injected.
     */

    public ProjectController(ProjectService projectService, ProjectFreelancerService pfService, FreelancerService freelancerService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.pfService = pfService;
        this.freelancerService = freelancerService;
        this.projectMapper = projectMapper;
    }

    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProjectDTO.class)))})
    })
    @GetMapping // GET: localhost:8080/api/v1/projects
    public ResponseEntity<Collection<Project>> getAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @Operation(summary = "Gets a project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Projects with supplied ID does not exist",
                    content = @Content)
    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/projects/1
    public ResponseEntity<Project> findById(@PathVariable String id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @Operation(summary = "Searches after projects by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProjectDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "Projects with supplied ID does not exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("search/name") // GET: localhost:8080/api/v1/projects/search/name?name=aProjectName
    public ResponseEntity<Collection<Project>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(projectService.findAllByName(name));
    }

    @Operation(summary = "Searches for projects by field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectDTO.class))}),
            @ApiResponse(responseCode = "406",
                    description = "No projects where found with given field",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ApiErrorResponse.class)))})
    })
    @GetMapping("search/field") // GET: localhost:8080/ap/v1/projects/search/field?field=aProjectName
    public ResponseEntity<Collection<Project>> findByField(@RequestParam String field) {
        return ResponseEntity.ok(projectService.findAllByField(field));
    }

    @Operation(summary = "Adds a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Project successfully created",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Project with supplied ID not found ",
                    content = @Content)
    })
    @PostMapping // POST: localhost:8080/api/v1/projects
    public ResponseEntity add(@RequestBody Project project) {
        SecurityContext sch = SecurityContextHolder.getContext();
        Authentication auth = sch.getAuthentication();
        if(Objects.equals(project.getId(), auth.getName()))
            return ResponseEntity.badRequest().build();
        Project aProject = projectService.add(project);
        URI location = URI.create("projects/" + aProject.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Project successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Project with supplied ID not found ",
                    content = @Content)
    })
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/projects/1
    public ResponseEntity update(@RequestBody ProjectDTO projectDTO, @PathVariable String id) {
        // Validates if body is correct
        if (id != projectDTO.getId())
            return ResponseEntity.badRequest().build();
        projectService.update(
                projectMapper.projectDTOToProject(projectDTO)
        );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Apply to project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Project or Freelancer with supplied ID doesn't exist",
                    content = @Content),
            @ApiResponse(responseCode = "204",
                    description = "Successfully applied to project",
                    content = @Content)
    })
    @PostMapping("{pId}/{fId}")
    public ResponseEntity apply(@RequestBody String motivation, @PathVariable String pId, @PathVariable String fId) {
        SecurityContext sch = SecurityContextHolder.getContext();
        Authentication auth = sch.getAuthentication();
        if (!projectService.exists(pId) && !Objects.equals(auth.getName(), fId))
            return ResponseEntity.badRequest().build();
        Project project = projectService.findById(pId);
//        project.getProjectFreelancers().forEach(id -> {
//            if (id.getFreelancer().getId().equals(fId))
//                throw new ProjectFreelancerAlreadyExists(fId);
//        });
        ProjectFreelancerKey pfKey = new ProjectFreelancerKey();
        ProjectFreelancer pf = new ProjectFreelancer();
        pfKey.setProject_id(pId);
        pfKey.setFreelancer_id(fId);

        pf.setId(pfKey);
        pf.setProject(projectService.findById(pId));
        pf.setMotivation(motivation);
        pf.setFreelancer(freelancerService.findById(fId));
        pf.setRole(Role.applicant);
        pfService.add(pf);

        project.addProjectFreelancer(pf);
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add or deny a application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Applicant accepted or denied",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content)
    })
    @PatchMapping("{pId}/{fId}/respond")
    public ResponseEntity respond(@RequestBody boolean bool, @PathVariable String pId, @PathVariable String fId) {
        ProjectFreelancer pf = pfService.findById(fId + pId);
        if (bool) {
            pf.setRole(Role.collaborator);
        } else {
            pf.setRole(Role.denied);
        }
        pfService.update(pf);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "410",
                    description = "Project successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Project with supplied ID not found ",
                    content = @Content)
    })
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/projects/1
    public ResponseEntity delete(@PathVariable String id) {
        SecurityContext sch = SecurityContextHolder.getContext();
        Authentication auth = sch.getAuthentication();
        if(Objects.equals(id, auth.getName()))
            return ResponseEntity.badRequest().build();

        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
