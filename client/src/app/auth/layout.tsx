import React from "react";
import Image from "next/image";

const AuthLayout = ({children}: { children: React.ReactNode }) => {
    return (
        <div className={``}>
            {children}

        </div>
    )
}
export default AuthLayout