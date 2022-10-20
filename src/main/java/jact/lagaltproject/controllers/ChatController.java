package jact.lagaltproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jact.lagaltproject.models.Chat;
import jact.lagaltproject.models.dtos.chat.ChatDTO;
import jact.lagaltproject.services.chat.ChatService;
import jact.lagaltproject.services.freelancer.FreelancerService;
import jact.lagaltproject.services.message.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/chats")
public class ChatController {


    private final ChatService chatService;
    private final MessageService messageService;
    private final FreelancerService freelancerService;

    /*
     *  Abase URL is defined and the relevant service is injected.
     */

    public ChatController(ChatService chatService, MessageService messageService, FreelancerService freelancerService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.freelancerService = freelancerService;
    }

    @Operation(summary = "Get all chats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ChatDTO.class)))})
    })
    @GetMapping // GET: localhost:8080/api/v1/chats
    public ResponseEntity<Collection<Chat>> getAll() {
        return ResponseEntity.ok(chatService.findAll());
    }

    @Operation(summary = "Gets a chat by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Chat.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Chat with supplied ID does not exist",
                    content = @Content)
    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/chats/1
    public ResponseEntity<Chat> findById(@PathVariable long id) {
        return ResponseEntity.ok(chatService.findById(id));
    }

    @Operation(summary = "Adds a chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Chat successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Chat with supplied ID not found ",
                    content = @Content)
    })
    @PostMapping // POST: localhost:8080/api/v1/chats
    public ResponseEntity add(@RequestBody Chat chat) {
        Chat aChat = chatService.add(chat);
        URI location = URI.create("chats/" + aChat.getId());
        return ResponseEntity.created(location).build();
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Chat successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Chat with supplied ID not found ",
                    content = @Content)
    })
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/chats/1
    public ResponseEntity update(@RequestBody Chat aChat, @PathVariable int id) {
        // Validates if body is correct
        if (id != aChat.getId())
            return ResponseEntity.badRequest().build();
        chatService.update(aChat);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "410",
                    description = "Chat successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Chat with supplied ID not found ",
                    content = @Content)
    })
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/chats/1
    public ResponseEntity delete(@PathVariable long id) {
        chatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
