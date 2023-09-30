import Swal from "sweetalert2";
class SweetAlertService{
     showNotificationAlert(title, text, icon){
        Swal.fire({
            title: title, 
            text: text, 
            icon: icon
          });
     }
     showDeleteNotificationAlert(title, text){
        return new Promise((resolve) => {
        Swal.fire({
        title: title,
        text: text,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it',
        cancelButtonText: 'No, keep it',
    }).then((result) => {
        resolve(result);
      });
    });
    }
    showQuizInstructionAlert() {
        return new Promise((resolve) => {
          Swal.fire({
            title: 'Quiz Instructions',
            text: 'Please read the following instructions before starting the quiz:\n\n1. Answer all questions to the best of your ability.\n2. You have a limited time to complete the quiz.\n3. Once started, the quiz cannot be paused or restarted.',
            icon: 'info',
            showCancelButton: true,
            confirmButtonText: 'Start Quiz',
            cancelButtonText: 'Cancel',
          }).then((result) => {
            resolve(result);
          });
        });
      }
      showManualSubmitAlert(){
        return new Promise((resolve)=>{
          Swal.fire({
            title: 'Submit Answers',
            text: 'Are you sure you want to submit your answers?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Yes, submit',
            cancelButtonText: 'No, cancel',
          }).then((result)=>{
            resolve(result);
          })
        })
      }
      showAutoSubmitAlert(handleSubmit){
        Swal.fire({
          text: 'Time has ended. The test will be submitted automatically.',
          icon: 'info',
          timer: 1000, 
          timerProgressBar: true,
          showConfirmButton: false,
        }).then(() => {
          handleSubmit();
        });
      }
}
export default new SweetAlertService();

