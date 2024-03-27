import React from "react";
import {TrendingUp} from "lucide-react";

const Trending:React.FC=()=>{
    return(
        <div className={`p-10`}>
            <div className={`flex`}>
            <TrendingUp />
                <span className={`font-poppins`}>Hot right now</span>
            </div>
        </div>
    )
}
export default Trending