package api.portfolio.service;

import api.portfolio.dto.ContactDTO;
import api.portfolio.exception.ResourceNotFoundException;
import api.portfolio.mapper.PortfolioMapper;
import api.portfolio.model.Contact;
import api.portfolio.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final PortfolioMapper portfolioMapper;
    private static final String CONTACT_NOT_FOUND_MESSAGE = "Contact not found with id: ";
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(portfolioMapper::contactToContactDTO)
                .toList();
    }

    public Optional<ContactDTO> getContactById(Long contactId) {
        return Optional.ofNullable(contactRepository.findById(contactId)
                .map(portfolioMapper::contactToContactDTO)
                .orElseThrow(() -> new ResourceNotFoundException(CONTACT_NOT_FOUND_MESSAGE + contactId)));
    }

    public Optional<ContactDTO> getContactByEmail(String email) {
        Contact contact = contactRepository.findByEmail(email);
        return Optional.ofNullable(portfolioMapper.contactToContactDTO(contact));
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = portfolioMapper.contactDTOToContact(contactDTO);
        Contact savedContact = contactRepository.save(contact);
        return portfolioMapper.contactToContactDTO(savedContact);
    }

    public Optional<ContactDTO> updateContact(Long contactId, ContactDTO contactDTO) {
        return Optional.ofNullable(contactRepository.findById(contactId)
                .map(existingContact -> {
                    existingContact.setPhone(contactDTO.getPhone());
                    existingContact.setEmail(contactDTO.getEmail());
                    existingContact.setAddress(contactDTO.getAddress());
                    Contact updatedContact = contactRepository.save(existingContact);
                    return portfolioMapper.contactToContactDTO(updatedContact);
                })
                .orElseThrow(() -> new ResourceNotFoundException(CONTACT_NOT_FOUND_MESSAGE + contactId)));
    }

    public void deleteContact(Long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new ResourceNotFoundException(CONTACT_NOT_FOUND_MESSAGE + contactId);
        }
        contactRepository.deleteById(contactId);
    }
}