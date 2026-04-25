
import { Navigate } from "react-router-dom";

let Protect = ({children}) => {
    let isauth=localStorage.getItem('isauth');

    if(isauth){
        return <Navigate to='/login/'></Navigate>
    
    }


}

export default Protect