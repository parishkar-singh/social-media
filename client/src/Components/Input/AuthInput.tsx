import { IconType } from "react-icons";
import React from "react";
import { UseFormRegister } from "react-hook-form";

interface AuthInputProps {
    icon: IconType;
    type: string;
    placeholder?: string;
    name: string;
    register: UseFormRegister<any>;
    error?: string | undefined;
}

const AuthInput: React.FC<AuthInputProps> = ({
                                                 icon: Icon,
                                                 type,
                                                 placeholder,
                                                 name,
                                                 register,
                                                 error,
                                             }) => {
    return (
        <div className={`flex flex-col  w-full gap-1`}>
            <div className={`flex w-full bg-white dark:text-white dark:bg-neutral-800  rounded-3xl text-black items-center gap-2 px-4 py-2 border-2`}>
                <Icon />
                <input
                    className={`w-full text-black dark:text-white bg-white dark:bg-neutral-800 border-none outline-none`}
                    type={type}
                    placeholder={placeholder}
                    {...register(name)}
                />
            </div>
            {error && (
                <p className={`text-red-500 text-sm`}>
                    {error}
                </p>
            )}
        </div>
    );
};

export default AuthInput;