import React from "react";
import {twMerge} from "tw-merge";

interface ButtonProps {
    text?: string;
    onClick?: (e:any) => void;
    disabled?: boolean;
    children?: React.ReactNode;
    className?: string;
}

const Button: React.FC<ButtonProps> = ({
                                           text,
                                           onClick,
                                           disabled,
                                           children,
                                           className
                                       }) => {
    return (<div>
            <button
                onClick={onClick}
                className={twMerge(`transition duration-300 bg-neutral-900 text-white dark:  hover:bg-[#5a54e0] hover:text-white dark:hover:text-black  font-bold py-2 px-4 rounded-2xl dark:text-black ` + className)}
                disabled={disabled}
            >
                {children?children:text}
            </button>
        </div>
    )
}
export default Button;