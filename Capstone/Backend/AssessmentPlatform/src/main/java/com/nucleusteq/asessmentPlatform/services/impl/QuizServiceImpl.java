package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nucleusteq.asessmentPlatform.dto.QuizDto;
import com.nucleusteq.asessmentPlatform.entities.Quiz;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.QuizRepo;
import com.nucleusteq.asessmentPlatform.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private QuizRepo quizRepo;

	@Override
	public QuizDto addQuiz(QuizDto quizDto) {
		Quiz quiz = this.dtoToQuiz(quizDto);
		Quiz newQuiz = quizRepo.save(quiz);
		return this.quizToDto(newQuiz);
	}

	@Override
	public List<QuizDto> getAllQuiz() {
		List<Quiz> quizzes = quizRepo.findAll();
		List<QuizDto> quizDtos = quizzes.stream().map(quiz -> this.quizToDto(quiz)).collect(Collectors.toList());
		return quizDtos;
	}

	@Override
	public QuizDto getQuizById(int quizId) {
		Quiz quiz = quizRepo.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id " + quizId));
		return this.quizToDto(quiz);
	}

	@Override
	public QuizDto updateQuiz(QuizDto quizDto, int quizId) {
		Quiz updatedQuiz = quizRepo.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("quiz not found"));
		updatedQuiz.setTitle(quizDto.getTitle());
		updatedQuiz.setDescription(quizDto.getDescription());
		quizRepo.save(updatedQuiz);
		return quizToDto(updatedQuiz);
	}

	@Override
	public String deleteQuiz(int quizId) {
		Quiz quiz = quizRepo.findById(quizId)
				.orElseThrow(() -> new ResourceNotFoundException("quiz not found with id " + quizId));
		quizRepo.delete(quiz);
		return quizId + " deleted sucessfully";
	}

	public final QuizDto quizToDto(final Quiz quiz) {
		QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
		return quizDto;
	}

	public final Quiz dtoToQuiz(final QuizDto quizDto) {
		Quiz quiz = this.modelMapper.map(quizDto, Quiz.class);
		return quiz;
	}

}
