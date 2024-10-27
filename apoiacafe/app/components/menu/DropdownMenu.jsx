'use client'
import { Menu } from "@mui/icons-material";
import { useRouter } from "next/navigation";

import { useState } from "react";

export default function DropdownMenu() {
    const [menuOpen, setMenuOpen] = useState(false);
    const router = useRouter();

    const onClickLogin = () => {
        router.push("/account/auth");
    };

    return (
    <div className="absolute top-5 right-5">
        <button onClick={() => setMenuOpen(!menuOpen)} className="text-white">
        <Menu fontSize="large" />
        </button>
        {menuOpen && (
        <div className="absolute top-12 right-0 bg-white text-purple-700 shadow-md rounded-lg py-4 px-6 z-10">
            <ul className="flex flex-col space-y-4">
            <li>
                <button onClick={onClickLogin} className="hover:text-purple-900 transition duration-200">Iniciar Sessão</button>
            </li>
            <li>
                <hr />
            </li>
            <li>
                <button className="hover:text-purple-900 transition duration-200">Registrar</button>
            </li>
            </ul>
        </div>
        )}
    </div>
    );
}
