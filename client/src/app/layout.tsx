
import type { Metadata } from "next";
import { Inter, Poppins, Ubuntu, Playfair_Display, Merriweather, Merriweather_Sans } from "next/font/google";
import "./globals.css";
import React from "react";
import { Providers } from "@/Redux/Providers";
interface ProvidersProps {
    children: React.ReactNode;
}
const inter = Inter({ subsets: ['latin'], variable: '--font-inter' });
const poppins = Poppins({ weight: ["400", "500"], subsets: ['latin'], variable: '--font-poppins' });
const ubuntu = Ubuntu({ weight: ["400", "500"], subsets: ['latin'], variable: '--font-ubuntu' });
const playfair = Playfair_Display({ weight: ["400", "500", "600", "700", "800", "900"], subsets: ['latin'], variable: '--font-playfair' });
const merri = Merriweather_Sans({ weight: ["300", "400", "500", "600", "700", "800"], subsets: ['latin'], variable: '--font-merri' });

export const metadata: Metadata = {
    title: "High",
    description: "Spring-Boot, NextJS",
};

const RootLayout: React.FC<{ children: React.ReactNode }> = ({ children }) => { // Ensure correct props type
    return (
        <Providers>
            <html lang="en">
            <body className={` ${merri.variable} ${poppins.variable} ${ubuntu.variable} ${inter.variable} ${playfair.variable} font-inter  bg-white dark:bg-black transition duration-200`}>
            {children}
            </body>
            </html>
        </Providers>
    );
}

export default RootLayout;
