import Swal from "sweetalert2";
class SweetAlertService {
  showNotificationAlert(title, text, icon) {
    Swal.fire({
      title: title,
      text: text,
      icon: icon,
    });
  }
  showDeleteNotificationAlert(title, text) {
    return new Promise((resolve) => {
      Swal.fire({
        title: title,
        text: text,
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, delete it",
        cancelButtonText: "No, keep it",
      }).then((result) => {
        resolve(result);
      });
    });
  }
  showQuizInstructionAlert() {
    return new Promise((resolve) => {
      Swal.fire({
        title: "Quiz Instructions",
        html: `
        <div style="text-align: left;">
        <p>Please read the following instructions before starting the quiz:</p>
        <ol>
        <li>Answer all questions to the best of your ability.</li>
        <li>You have a limited time to complete the quiz. Please note that refreshing the page or leaving the quiz will result in automatic submission, so make sure to manage your time wisely.</li>
        <li>Each question carries 1 mark.</li>
        </ol>
        </div>
        <p>Good luck with your quiz!</p>
        `,
        icon: "info",
        showCancelButton: true,
        confirmButtonText: "Start Quiz",
        cancelButtonText: "Cancel",
      }).then((result) => {
        resolve(result);
      });
    });
  }
  showManualSubmitAlert() {
    return new Promise((resolve) => {
      Swal.fire({
        title: "Submit Answers",
        text: 'Are you sure you want to submit your answers?',
        icon: "question",
        showCancelButton: true,
        confirmButtonText: "Yes, submit",
        cancelButtonText: "No, cancel",
      }).then((result) => {
        resolve(result);
      });
    });
  }
  showAutoSubmitAlert(handleSubmit) {
    Swal.fire({
      text: "Time has ended. The test will be submitted automatically.",
      icon: "info",
      timer: 1000,
      timerProgressBar: true,
      showConfirmButton: false,
    }).then(() => {
      handleSubmit();
    });
  }
}
export default new SweetAlertService();
