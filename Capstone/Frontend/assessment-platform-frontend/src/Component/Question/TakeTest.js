// import React, { useEffect, useState } from 'react'
// import axios from 'axios';
// import { useParams } from 'react-router-dom';

// export default function TakeTest() {
//     const[questions, setQuestions]= useState([]);
//     const {quizId} =useParams();
//     useEffect(()=>{
//         loadQuestions();
//       })
    
//           const loadQuestions = async () => {
//             try {
//               const result = await axios.get(`http://localhost:8080/ques/quiz/${quizId}`);
//               setQuestions(result.data);
//             } catch (error) {
//               console.error("Error loading questions:", error);
//             }
//           };
//   return (
//     <div>TakeTest</div>
//   )
// }
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

export default function TakeTest() {
  const [questions, setQuestions] = useState([]);
  const [selectedOptions, setSelectedOptions] = useState({}); // To store selected options
  const { quizId } = useParams();

  useEffect(() => {
    loadQuestions();
  }, [quizId]);

  const loadQuestions = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/ques/quiz/${quizId}`);
      setQuestions(result.data);
    } catch (error) {
      console.error("Error loading questions:", error);
    }
  };

  const handleOptionSelect = (questionId, option) => {
    const updatedSelectedOptions = { ...selectedOptions };
    updatedSelectedOptions[questionId] = option;
    setSelectedOptions(updatedSelectedOptions);
  };

  return (
    <div>
      <h1>Take Test</h1>
      <ul>
        {questions.map((question) => (
          <li key={question.id}>
            <h3>{question.questionText}</h3>
            <ul>
              {question.options.map((option) => (
                <li key={option.id}>
                  <label>
                    <input
                      type="radio"
                      name={`question_${question.id}`}
                      value={option.id}
                      checked={selectedOptions[question.id] === option.id}
                      onChange={() => handleOptionSelect(question.id, option.id)}
                    />
                    {option.optionText}
                  </label>
                </li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </div>
  );
}
