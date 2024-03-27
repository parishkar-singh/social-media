// Navbar.tsx
import React from "react";
import ThemeToggle from "@/Components/ThemeToggle";
import { addTransition, darkModeBottomSeperator } from "@/Utils/DarkMode";
import NavbarLinks from "@/Components/Item/NavbarItem";

const Logo: React.FC = () => {
    return (
        <div className={`flex items-center gap-4 font-black`}>
            <div className={`bg-black dark:bg-violet-500 rounded-full w-10 h-10 transform translate-z-20`}></div>
            <div className={`bg-black dark:bg-violet-500 rounded-full w-10 h-10 skew-x-12 skew-y-12 skew-z-[24deg]`}></div>
            <div className={`bg-black dark:bg-violet-500 rounded-full w-10 h-10  skew-x-[50deg]`}></div>
            <span className={"font-playfair dark:dark:text-violet-500 ml-2 text-2xl"}>High</span>
        </div>
    );
};

const Navbar: React.FC = () => {
    return (
        <div className={`fixed top-0 left-0 w-full backdrop-blur-md flex items-center justify-between py-3 px-4 z-10 ` + darkModeBottomSeperator + addTransition}>
            <Logo />
            <div className={`flex items-center gap-4`}>
                <NavbarLinks />
                <ThemeToggle />
            </div>
        </div>
    );
};

export default Navbar;
