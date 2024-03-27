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
        <button className={`transition bg-neutral-900 dark:bg-gray-100 duration-300 font-bold py-2 px-8 rounded-3xl`} onClick={handleThemeSwitch} >
            {theme!=='dark'?<MoonStar className={`text-yellow-500`} />:<Sun className={`text-yellow-500 `} />}
        </button>

    )
}
export default ThemeToggle;