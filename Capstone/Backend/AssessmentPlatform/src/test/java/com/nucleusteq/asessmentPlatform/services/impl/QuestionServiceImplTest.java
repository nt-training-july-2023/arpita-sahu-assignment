package com.nucleusteq.asessmentPlatform.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.nucleusteq.asessmentPlatform.dto.QuestionDto;
import com.nucleusteq.asessmentPlatform.entities.Question;
import com.nucleusteq.asessmentPlatform.entities.Quiz;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.QuestionRepo;
import com.nucleusteq.asessmentPlatform.repositories.QuizRepo;

class QuestionServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private QuestionRepo questionRepo;
    
    @Mock
    private QuizRepo quizRepo;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddQuestion_Success() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuesId(1);
        questionDto.setQuestion("what is java");
        questionDto.setOption1("a");
        questionDto.setOption2("b");
        questionDto.setOption3("c");
        questionDto.setOption4("d");
        questionDto.setAnswer("a");
        questionDto.setQuizId(1);
 
        Quiz quiz = new Quiz();
        quiz.setQuizId(questionDto.getQuizId());
        Question question = new Question();
        question.setQuesId(questionDto.getQuesId());
        question.setQuestion(questionDto.getQuestion());
        question.setOption1(questionDto.getOption1());
        question.setOption2(questionDto.getOption2());
        question.setOption3(questionDto.getOption3());
        question.setOption4(questionDto.getOption4());
        question.setAnswer(questionDto.getAnswer());
        question.setQuiz(quiz);
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(quizRepo.findById(questionDto.getQuizId())).thenReturn(Optional.of(quiz));
        when(questionRepo.save(question)).thenReturn(question);
        QuestionDto resultDto = questionService.addQuestion(questionDto);
        assertEquals(resultDto, questionDto);
        assertEquals(resultDto.getQuizId(), questionDto.getQuizId());
        assertEquals(resultDto.getQuestion(), questionDto.getQuestion());

    }
    
    @Test
    public void testAddQuestionQuizNotFound() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuizId(1); 
        when(quizRepo.findById(questionDto.getQuizId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            questionService.addQuestion(questionDto);
        });
    }

    @Test
    public void testAddQuestion_EmptyQuestion() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestion("");
        questionDto.setQuizId(1);
        Question question = new Question();
        question.setQuestion(questionDto.getQuestion());
        Quiz quiz = new Quiz();
        quiz.setQuizId(questionDto.getQuizId());
        question.setQuiz(quiz);
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(quizRepo.findById(questionDto.getQuizId())).thenReturn(Optional.of(quiz));
        BadCredentialsException exception = assertThrows(
                BadCredentialsException.class,
                () -> questionService.addQuestion(questionDto));
        assertEquals("Question must not be empty", exception.getMessage());
    }

    @Test
    public void testGetAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1, "Question Name", "option1", "option2",
                "option3", "option4", "answer"));
        List<QuestionDto> expectedQuestionDtos = questions.stream()
                .map(question -> questionService.quesToDto(question))
                .collect(Collectors.toList());
        when(questionRepo.findAll()).thenReturn(questions);
        List<QuestionDto> actualQuestionDtos = questionService
                .getAllQuestions();
        assertEquals(expectedQuestionDtos, actualQuestionDtos);
    }

    @Test
    public void testGetQuestionById() {
        int quesId = 1;
        Question question = new Question(1, "Question Name", "option1",
                "option2", "option3", "option4", "answer");
        when(questionRepo.findById(quesId)).thenReturn(Optional.of(question));
        QuestionDto actualQuestionDto = questionService.getQuestionById(quesId);
        QuestionDto expectedQuestionDto = questionService.quesToDto(question);
        assertEquals(expectedQuestionDto, actualQuestionDto);
    }

    @Test
    public void testGetQuestionByIdNotFound() {
        int nonExistentQuesId = 999;
        when(questionRepo.findById(nonExistentQuesId))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            questionService.getQuestionById(nonExistentQuesId);
        });
    }
    @Test
    public void testUpdateQuestion() {
        int quesId = 1;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestion("Updated Question");
        questionDto.setOption1("Option 1");
        questionDto.setOption2("Option 2");
        questionDto.setOption3("Option 3");
        questionDto.setOption4("Option 4");
        questionDto.setAnswer("Option 4");
        Question existingQuestion = new Question(1, "Question Name", "option1",
                "option2", "option3", "option4", "option3");
        when(modelMapper.map(existingQuestion, QuestionDto.class)).thenReturn(questionDto);
        when(questionRepo.findById(quesId)).thenReturn(Optional.of(existingQuestion));
        QuestionDto updatedQuestionDto = questionService.updateQuestion(questionDto, quesId);
        assertEquals(questionDto, updatedQuestionDto);

    }

    @Test
    public void testUpdateQuestionNotFound() {
        int nonExistentQuesId = 999;
        when(questionRepo.findById(nonExistentQuesId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            QuestionDto questionDto = new QuestionDto();
            questionService.updateQuestion(questionDto, nonExistentQuesId);
        });
    }
    @Test
    public void testDeleteQuestion() {
        int quesId = 1;
        Question question = new Question(1, "Question Name", "option1",
                "option2", "option3", "option4", "answer");
        when(questionRepo.findById(quesId)).thenReturn(Optional.of(question));
        String result = questionService.deleteQuestion(quesId);
        String expectedMessage = "Question deleted Successfully";
        assertEquals(expectedMessage, result);
        
    }
    
    @Test
    public void testGetQuestionByQuizId() {
        int quizId=1;
        List<Question> quesList = new ArrayList<Question>();
        Question question = new Question();
        question.setQuesId(1);
        question.setQuestion("Question");
        question.setOption1("a");
        question.setOption2("b");
        question.setOption3("c");
        question.setOption4("d");
        question.setAnswer("a");
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuesId(question.getQuesId());
        questionDto.setQuestion(question.getQuestion());
        questionDto.setOption1(question.getOption1());
        questionDto.setOption2(question.getOption2());
        questionDto.setOption3(question.getOption3());
        questionDto.setOption4(question.getOption4());
        questionDto.setAnswer(question.getAnswer());
        
        quesList.add(question);
        
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        when(questionRepo.findQuestionByQuizId(quizId)).thenReturn(quesList);
        List<QuestionDto> questionDtos = questionService.getQuestionsByQuizId(quizId);
        assertNotNull(questionDtos);
        assertEquals(1, questionDtos.size());
        assertEquals("Question", questionDtos.get(0).getQuestion());
    }

    @Test
    public void testDeleteQuestionNotFound() {
      
        int nonExistentQuesId = 1; 
        when(questionRepo.findById(nonExistentQuesId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {questionService.deleteQuestion(nonExistentQuesId);
        });
    }


}
