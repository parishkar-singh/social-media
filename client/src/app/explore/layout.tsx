import React from "react";
import Image from "next/image";
import Navbar from "@/Components/UI/Navbar";

const ExploreLayout = ({children}: { children: React.ReactNode }) => {
    return (
        <div className={''}>
            {children}

        </div>
    )
}
export default ExploreLayout
