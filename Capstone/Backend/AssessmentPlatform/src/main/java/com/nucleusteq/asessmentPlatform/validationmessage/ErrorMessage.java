package com.nucleusteq.asessmentPlatform.validationmessage;

/**
 * The ErrorMessage class contains constant error message strings that can be
 * used throughout the application to provide consistent error messages.
 */
public final class ErrorMessage {
    /**
     * default constructor.
     */
    private ErrorMessage() {
    }
    /**
     * Error message for when a category is not found with a specific ID.
     */
    public static final String CATEGORY_ID_NOT_FOUND =
            "Category not found with ID ";

    /**
     * Error message for when a category is not found.
     */
    public static final String CATEGORY_NOT_FOUND = "Category not found";

    /**
     * Prefix for error message when a category already exists.
     */
    public static final String CATEGORY_ALREADY_EXISTS_PREFIX =
            "Category with title '";

    /**
     * Suffix for error message when a category already exists.
     */
    public static final String CATEGORY_ALREADY_EXISTS_SUFFIX =
            "' already exists.";

    /**
     * Error message for when a quiz ID is not found.
     */
    public static final String QUIZ_NOT_FOUND = "Quiz ID not found";

    /**
     * Error message for when a quiz is not found with a specific ID.
     */
    public static final String QUIZID_NOT_FOUND =
            "Quiz not found with ID ";

    /**
     * Error message for when a question is empty.
     */
    public static final String QUESTION_EMPTY =
            "Question must not be empty";

    /**
     * Error message for when a question is not found with a specific ID.
     */
    public static final String QUESTIONID_NOT_FOUND =
            "Question not found with ID ";

    /**
     * Error message for when a question is not found.
     */
    public static final String QUESTION_NOT_FOUND = "Question Not Found.";

    /**
     * Prefix for error message when a quiz already exists.
     */
   public static final String QUIZ_ALREADY_EXISTS_PREFIX = "Quiz with title '";

    /**
     * Suffix for error message when a quiz already exists.
     */
    public static final String QUIZ_ALREADY_EXISTS_SUFFIX = "' already exists.";

    /**
     * Error message for when an email address already exists.
     */
    public static final String EMAIL_ALREADY_EXIST =
            "Email address already exists";

    /**
     * Error message for when a user is not found with a specific ID.
     */
    public static final String USERID_NOT_FOUND = "User not found with ID ";

    /**
     * Error message for when a user is not found.
     */
    public static final String USER_NOT_FOUND = "User not found";

    /**
     * Error message for when a password is invalid.
     */
    public static final String INVALID_PASSWORD = "Invalid password";
    /**
     * Error message for when category title already exist.
     */
    public static final String CATEGORY_TITLE_ALREADY_EXISTS =
            "Category title already exist";
}
