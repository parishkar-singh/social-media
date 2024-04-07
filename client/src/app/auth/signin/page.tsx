'use client'
import axios from "axios";
import Image from "next/image";
import Link from "next/link";
import { useForm } from "react-hook-form";
import { MdOutlineMailOutline } from "react-icons/md";
import { AiFillFacebook, AiFillGoogleCircle, AiOutlineLock } from "react-icons/ai";
import { useRouter } from "next/navigation";
import { object, string, TypeOf } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import React, { useEffect, useState } from "react";
import { FcGoogle } from "react-icons/fc";
import AuthInput from "@/Components/Input/AuthInput";
import { useDispatch } from "react-redux";
import { setUser } from "@/Redux/userSlice";

const createSessionSchema = object({
    email: string().nonempty({
        message: "Email is required",
    }),
    password: string().min(6).nonempty({
        message: "Password is required",
    }),
});

interface Payload {
    id: string;
    username: string;
    email: string;
    picture?: string;
}

type CreateSessionInput = TypeOf<typeof createSessionSchema>;

const SignIn = () => {
    const router = useRouter();
    const { register, formState: { errors }, handleSubmit } = useForm<CreateSessionInput>({
        resolver: zodResolver(createSessionSchema)
    });

    const onSubmit = async (data: CreateSessionInput) => {
        try {
            const response = await axios.post(`${process.env.NEXT_PUBLIC_SERVER_ENDPOINT}/auth/signin`, data);
            const { accessToken, refreshToken } = response.data;

            // Store tokens in local storage or cookies for future requests
            localStorage.setItem('accessToken', accessToken);
            localStorage.setItem('refreshToken', refreshToken);

            router.push('/');
        } catch (error:any) {
            // Handle authentication error
            console.error("Authentication failed:", error.message);
        }
    }


    return (
        <div className={`transition duration-200 bg-white dark:bg-black p-6 md:p-24  flex flex-col items-center justify-center`}>
            <div className={` flex flex-col  items-center  gap-8 `}>
                <h1 className={`text-neutral-900 text-2xl md:text-4xl dark:text-white text-center md:text-start font-bold`}>Sign In</h1>
                <div className={`w-full `}>
                    <hr />
                </div>
                <form className={`flex flex-col justify-center items-center`} onSubmit={handleSubmit(onSubmit)}>
                    <div className={`flex  flex-col gap-4`}>
                        <AuthInput
                            icon={MdOutlineMailOutline}
                            placeholder={`Your email address`}
                            type={`email`}
                            name={'email'}
                            register={register}
                            error={errors.email?.message as string | undefined}
                        />
                        <AuthInput
                            icon={AiOutlineLock}
                            placeholder={`Enter your password`}
                            type={`password`}
                            name={'password'}
                            register={register}
                            error={errors.password?.message as string | undefined}
                        />
                    </div>
                    <button type={`submit`} className={`bg-[#5b54e0] mt-8 p-2 px-14 rounded-lg text-white`}>Sign In
                    </button>
                </form>
                <div className={`w-full `}>
                    <hr />
                </div>
                <div>
                    <span className={`text-neutral-500 font-poppins`}>using High for the first time?&nbsp;</span>
                    <Link className={`text-[#5b54e0] font-bold font-poppins `} href={`/auth/signup`}>
                        Sign up
                    </Link>
                </div>
            </div>
        </div>
    )
}

const mapResponseToPayload = (response: any): Payload => {
    return {
        id: response._id,
        username: response.name,
        email: response.email,
        picture: response.picture ? response.picture : undefined,
    };
};

export default SignIn;
