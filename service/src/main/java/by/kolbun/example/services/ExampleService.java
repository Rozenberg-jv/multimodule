package by.kolbun.example.services;

import by.kolbun.example.model.ExampleEntity;
import by.kolbun.example.repository.ExampleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

	@NonNull
	private final ExampleRepository exampleRepository;

	public String getTextById(long id) {

		return exampleRepository.findById(id).orElseThrow(RuntimeException::new).getText();
	}

	public long postNewEntity(String newText) {

		ExampleEntity newEntity = new ExampleEntity();
		newEntity.setText(newText);

		return exampleRepository.saveAndFlush(newEntity).getId();
	}
}
