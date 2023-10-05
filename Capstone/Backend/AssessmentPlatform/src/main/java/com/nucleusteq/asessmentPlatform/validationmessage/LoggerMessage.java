package com.nucleusteq.asessmentPlatform.validationmessage;

/**
 * The LoggerMessage class contains constant log message strings that can be
 * used throughout the application for logging different events and actions.
 */
public final class LoggerMessage {
    /**
     * default constructor.
     */
    private LoggerMessage() {
    }
    /**
     * Log message for a successful category addition.
     */
    public static final String SAVE_CATEGORY =
            "Category Added Successfully";

    /**
     * Log message for successfully retrieving a category.
     */
    public static final String GET_CATEGORY =
            "Category retrieved successfully.";

    /**
     * Log message for getting a category by its ID.
     */
    public static final String GET_CATEGORY_BY_ID = "Get category by ID";

    /**
     * Log message for a successful category update.
     */
    public static final String UPDATE_CATEGORY = "Category updated";

    /**
     * Log message for a successful category deletion.
     */
    public static final String DELETE_CATEGORY =
            "Category deleted";

    /**
     * Log message when a category with the same title already exists.
     */
    public static final String CATEGORY_TITLE_EXIST =
            "Category with Title already exists";

    /**
     * Log message for when a category is not found.
     */
    public static final String CATEGORY_NOT_FOUND =
            "Category not found";

    /**
     * Log message for saving a category in the Category Repository.
     */
    public static final String CATEGORY_SAVED_IN_REPOSITORY =
            "Category saved in Category Repository.";

    /**
     * Log message for a successful question addition.
     */
    public static final String SAVE_QUESTION = "Question Added Successfully.";

    /**
     * Log message for successfully retrieving a question.
     */
    public static final String GET_QUESTION =
            "Question retrieved successfully.";

    /**
     * Log message for getting questions by their ID.
     */
    public static final String GET_QUESTIONS_BY_ID =
            "Retrieved Questions by Question Id";

    /**
     * Log message for successful user registration.
     */
    public static final String REGISTER_USER =
            "User Register successfully";

    /**
     * Log message for getting questions by their quiz ID.
     */
    public static final String GET_QUESTIONS_BY_QUIZID =
            "Retrieved Questions by Quiz Id";

    /**
     * Log message for a successful question update.
     */
    public static final String UPDATE_QUESTION =
            "Question Updated Successfully.";

    /**
     * Log message for a successful question deletion.
     */
    public static final String DELETE_QUESTION =
            "Question deleted Successfully";

    /**
     * Log message for when a question is empty.
     */
    public static final String QUESTION_EMPTY = "Question must not be empty";

    /**
     * Log message for saving a question in the Question Repository.
     */
    public static final String QUESTION_SAVED_IN_REPOSITORY =
            "Question saved in Question Repository.";

    /**
     * Log message for successful quiz saving.
     */
    public static final String SAVE_QUIZ = "Quiz saved successfully";

    /**
     * Log message for successfully retrieving a quiz.
     */
    public static final String GET_QUIZ = "Quiz retrieved successfully.";

    /**
     * Log message for getting a quiz by its ID.
     */
    public static final String GET_QUIZ_BY_ID = "Get Quiz By Quiz Id";

    /**
     * Log message for getting quizzes by their category ID.
     */
    public static final String GET_QUIZ_BY_CATEGORYID =
            "Get Quiz By Category Id";

    /**
     * Log message for a successful quiz update.
     */
    public static final String UPDATE_QUIZ = "Quiz Updated Successfully.";

    /**
     * Log message for a successful quiz deletion.
     */
    public static final String DELETE_QUIZ = "Quiz deleted Successfully";

    /**
     * Log message when a quiz with the same title already exists.
     */
    public static final String QUIZ_TITLE_EXIST = "Quiz Title already exists";

    /**
     * Log message for when a quiz is not found.
     */
    public static final String QUIZ_NOT_FOUND = "Quiz not found";

    /**
     * Log message for a successful result addition.
     */
    public static final String SAVE_RESULT = "Result Added Successfully.";

    /**
     * Log message for successfully retrieving a result.
     */
    public static final String GET_RESULT = "Result retrieved successfully.";

    /**
     * Log message for getting results by a user's email.
     */
    public static final String GET_RESULT_BY_EMAIL =
            "Get Results by User email";

    /**
     * Log message for successfully retrieving a user.
     */
    public static final String GET_USER = "User retrieved successfully.";

    /**
     * Log message for a successful user deletion.
     */
    public static final String DELETE_USER = "User deleted successfully";

    /**
     * Log message for bad credentials during login.
     */
    public static final String BAD_CREDENTIAL = "Bad Credentials";

    /**
     * Log message for successful login.
     */
    public static final String LOGIN_SUCCESS = "Login successfully";

    /**
     * Log message for successful user registration.
     */
    public static final String SAVE_USER = "User Register Successfully";
}
