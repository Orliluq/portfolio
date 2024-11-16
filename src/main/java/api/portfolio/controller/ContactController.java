package api.portfolio.controller;

import api.portfolio.dto.ContactDTO;
import api.portfolio.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id) {
        Optional<ContactDTO> contact = contactService.getContactById(id);
        return contact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ContactDTO> getContactByEmail(@PathVariable String email) {
        Optional<ContactDTO> contact = contactService.getContactByEmail(email);
        return contact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO contactDTO) {
        ContactDTO createdContact = contactService.createContact(contactDTO);
        return ResponseEntity.ok(createdContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(
            @PathVariable Long id,
            @RequestBody ContactDTO contactDTO) {
        Optional<ContactDTO> updatedContact = contactService.updateContact(id, contactDTO);
        return updatedContact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}