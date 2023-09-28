import axios from "axios";

const USER_BASE_URL = 'http://localhost:8080/users/';
const CATEGORY_BASE_URL = 'http://localhost:8080/category/';
const QUIZ_BASE_URL = 'http://localhost:8080/quiz/';
const QUESTION_BASE_URL = 'http://localhost:8080/ques/';
const RESULT_BASE_URL = 'http://localhost:8080/result/';

class ServiceURL{

    userRegistration(User){
        return axios.post(USER_BASE_URL + 'register', User);
    }
    userLogin(EmailPassword){
        return axios.post(USER_BASE_URL + 'login', EmailPassword);
    }
    addCategory(Category){
        return axios.post(CATEGORY_BASE_URL +'save', Category);
    }
    updateCategory(id, Category){
        return axios.put(CATEGORY_BASE_URL +id, Category);
    }
    getCategoryById(id){
        return axios.get(CATEGORY_BASE_URL +id);
    }
    getCategories(){
        return axios.get(CATEGORY_BASE_URL );
    }
    deleteCategory(id){
        return axios.delete(CATEGORY_BASE_URL +id);

    }
    addQuiz(Quiz){
        return axios.post(QUIZ_BASE_URL +'add', Quiz);
    }
    updateQuiz(id,Quiz){
        return axios.put(QUIZ_BASE_URL +id, Quiz);
    }
    getQuizByQuizId(quizId){
        return axios.get(QUIZ_BASE_URL +quizId);
    }
    getQuizzes(){
        return axios.get(QUIZ_BASE_URL);
    }
    getQuizByCategoryId(categoryId){
        return axios.get(QUIZ_BASE_URL +'category/'+categoryId);
    }
    deleteQuiz(id){
        return axios.delete(QUIZ_BASE_URL +id);
    }
    addQuestion(Question){
        return axios.post(QUESTION_BASE_URL + 'save', Question);
    }
    updateQuestion(quesId, Question){
        return axios.put(QUESTION_BASE_URL +quesId, Question);
    }
    getQuestionByQuestionId(quesId){
        return axios.get(QUESTION_BASE_URL +quesId);
    }
    getQuestionByQuizId(quizId){
        return axios.get(QUESTION_BASE_URL +'quiz/'+quizId);
    }
    deleteQuestion(id){
        return axios.delete(QUESTION_BASE_URL +id);
    }
    addResult(Result){
        return axios.post(RESULT_BASE_URL +'save',Result);
    }
    getResults(){
        return axios.get(RESULT_BASE_URL);
    }
    getResultByUserEmail(userEmail){
        return axios.get(RESULT_BASE_URL + userEmail)
    }

}

export default new ServiceURL();