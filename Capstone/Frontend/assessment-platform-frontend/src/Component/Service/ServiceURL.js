import axios from "axios";

const USER_BASE_URL = 'http://localhost:8080/users';
const CATEGORY_BASE_URL = 'http://localhost:8080/category'

class ServiceURL{

    userRegistration(User){
        return axios.post(USER_BASE_URL + '/register', User);
    }
    userLogin(EmailPassword){
        return axios.post(USER_BASE_URL + '/login', EmailPassword);
    }
    addCategory(Category){
        return axios.post(CATEGORY_BASE_URL +'/save', Category);
    }
    updateCategory(id, Category){
        return axios.post(CATEGORY_BASE_URL +'/'+id, Category);
    }
    getCategoryById(id){
        return axios.get(CATEGORY_BASE_URL +'/'+id);
    }
}

export default new ServiceURL();