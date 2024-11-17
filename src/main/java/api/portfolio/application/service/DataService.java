package api.portfolio.application.service;

import api.portfolio.domain.model.Portfolio;
import api.portfolio.infrastructure.repository.PortfolioRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class DataService {

    private static final Logger logger = LoggerFactory.getLogger(DataService.class);
    private final PortfolioRepository portfolioRepository;

    public DataService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public String readDataJson() {
        StringBuilder jsonData = new StringBuilder();
        try {
            Resource resource = new ClassPathResource("data.json");
            logger.info("Resource exists: {}", resource.exists());
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            logger.info("JSON loaded: {}", jsonData);

            ObjectMapper objectMapper = new ObjectMapper();
            return processJsonData(jsonData.toString(), objectMapper);
        } catch (Exception e) {
            logger.error("Error reading data.json or inserting data", e);
            return "Error inserting data.";
        }
    }

    private String processJsonData(String jsonData, ObjectMapper objectMapper) {
        try {
            List<Portfolio> portfolios = objectMapper.readValue(jsonData, new TypeReference<>() {
            });
            portfolioRepository.saveAll(portfolios);
            return "Data inserted correctly.";
        } catch (JsonMappingException e) {
            JsonNode root = parseJson(jsonData, objectMapper);
            processJsonObject(root);
            return "Processed JSON object";
        } catch (Exception e) {
            logger.error("Error processing JSON", e);
            return "Error processing JSON.";
        }
    }

    @Contract(pure = true)
    private JsonNode parseJson(String jsonData, ObjectMapper objectMapper) {
        try {
            return objectMapper.readTree(jsonData);
        } catch (Exception e) {
            logger.error("Error parsing JSON", e);
            throw new UnsupportedOperationException("Failed to parse JSON data.");
        }
    }

    @Contract(pure = true)
    private void processJsonObject(JsonNode rootNode) {
        if (rootNode.has("someField")) {
        } else {
            logger.error("JSON structure unexpected: {}", rootNode);
        }
    }
}