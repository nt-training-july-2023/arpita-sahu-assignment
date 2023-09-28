import React, { useState, useEffect } from 'react'
import Navbar from '../Navbar/Navbar';
import DisableBackButton from '../DisableBackButton';
import ServiceURL from '../Service/ServiceURL';
export default function Result() {
    const[results, setResults]= useState([]);
    const role = localStorage.getItem('userRole');
    const userEmail = localStorage.getItem('selectedEmail');

    useEffect( () => {
      if(role === 'user')
        loadResults();
      if(role === 'admin')
      loadAllResult();
    },[role]);
    
    const loadResults = async () => {
        try {
          const response = await ServiceURL.getResultByUserEmail(userEmail);
          setResults(response.data);
        } catch (error) {
          console.error("Error loading results:", error);
        }
      };
    const loadAllResult = async () =>{
      try{
        const response = await ServiceURL.getResults();
        setResults(response.data);
      }catch(error){
        console.error("Error loading results:", error);
      }
    }
  return (
  <>
    <Navbar/>
    <DisableBackButton/>
          <div className="category-wrapper-container">
            <div className="category-card">
              <div className="category_wrapper">
              <h2 className="cat_head">Result</h2>
              {role === 'user' &&   <h2 className="cat_head"> Welcome  {userEmail}</h2>}
                <table className="category-table">
                  <thead>
                    <tr>
                    <th>ID</th>
                    {role === 'admin' && <>
                    <th>Name</th>
                    <th>Email</th></>}
                    <th>Category</th>
                    <th>Quiz</th>
                    <th>Obtained Marks</th>
                    <th>Total Marks</th>
                    <th>Number of Question Attempted</th>
                    <th>Total Question</th>
                    <th><center>Date & Time</center></th>
                    </tr>
                  </thead>
                  <tbody>
                    {results.map((result, index) => (
                      <tr key={index}>
                        <td>{++index}</td>
                        {role === 'admin' && <>
                        <td>{result.userName}</td>
                        <td>{result.email}</td></>}
                        <td>{result.categoryTitle}</td>
                        <td>{result.quizTitle}</td>
                        <td><center>{result.obtainedMarks}</center></td>
                        <td><center>{result.totalMarks}</center></td>
                        <td><center>{result.numofAttemptedQues}</center></td>
                        <td><center>{result.totalNumOfQues}</center></td>
                        <td>{result.dateAndTime}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div></>
  )
}
