import React from "react";
import {addTransition, darkModeContainerConfig} from "@/Utils/DarkMode";

const Header: React.FC = () => {
    return (
        <div className={'flex flex-col justify-center  h-[450px] bg-primary dark:bg-teal-600 w-full  dark:border-b dark:border-b-neutral-800 '+ addTransition}>
            <div className={`mt-3 gap-5 px-12 flex flex-col`}>
                <div className={`flex flex-col`}>
                <span className={`text-6xl font-bold font-playfair text-white dark:text-black`}>Stay curious.</span>
                <span className={`text-2xl font-playfair text-white dark:text-black `}>Discover stories, thinking, and expertise from writers on any topic.</span>
                </div>
            <button className={` w-52 py-2 font-poppins font-bold bg-white text-black dark:bg-black dark:text-white rounded-3xl `}>Read now</button>
            </div>
        </div>)
}

export default Header