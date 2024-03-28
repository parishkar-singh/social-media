'use client'
import {useEffect, useState} from "react";
import {MoonStar, Sun} from "lucide-react";

const ThemeToggle = () => {
    const [theme, setTheme] = useState('light');

    useEffect(() => {
        if (theme === 'dark') {
            document.documentElement.classList.add('dark')
        } else {
            document.documentElement.classList.remove('dark')
        }
    }, [theme])
    const handleThemeSwitch = (e: any) => {
        e.stopPropagation();
        e.preventDefault();
        setTheme(theme === 'dark' ? 'light' : 'dark')
    }
    return (
        <button className={`transition dark:bg-neutral-900 bg-white duration-300 font-bold p-2 rounded-3xl`} onClick={handleThemeSwitch} >
            {theme!=='dark'?<MoonStar className={`text-yellow-500`} />:<Sun className={`text-third `} />}
        </button>

    )
}
export default ThemeToggle;