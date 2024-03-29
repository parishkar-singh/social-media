import React from "react";

const Discover:React.FC=()=>{
    const ButtonStyles=" bg-neutral-200 rounded-full p-1 px-2"
    return(
        <div className={`p-10 w-1/3 flex flex-col`}>
            <span>Discover based on your likes</span>
            <div className={`flex gap-2`}>
            <button className={ButtonStyles}>Latest</button>
            <button className={ButtonStyles}>Relevant</button>
            <button className={ButtonStyles}>Popular</button>
            <button className={ButtonStyles}>Most Liked</button>
            </div>
        </div>
    )
}
export default Discover