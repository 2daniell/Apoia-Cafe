'use client'
import { useAuth } from "@/app/contexts/AuthContext";
import { useState } from "react";

function Auth() {
  
  const [isLogin, setIsLogin] = useState(true);
  const [ formData, setFormData ] = useState({
      email: "",
      password: "",
      name: "",
  });

  const { signIn } = useAuth;

  const toggleForm = () => setIsLogin(!isLogin);

  const handleSubmit = async (e) => {
      e.preventDefault();

      console.log(formData)

      if (isLogin) {
          try {
              await signIn({
                  email: formData.email,
                  password: formData.password
              });

          } catch (error) {
              console.log("Ocorreu um erro inesperado " + error);
          }
      }
  };

  const handleChange = (e) =>{
      
    const { name, value } = e.target;

    setFormData((formData) => ({ ...formData, [name]: value }));

  }

  return (
    <div className="relative min-h-screen flex items-center justify-center bg-gray-50 px-4 overflow-hidden">
      
      
      <div
        className={`absolute top-1/2 transform -translate-y-1/2 ${
          isLogin ? "translate-x-[80%] right-0" : "-translate-x-[80%] left-0"
        } w-[100vw] h-screen bg-purple-700 rounded-full transition-transform duration-1000 ease-in-out`}
      >
      </div>

      <section className="relative z-10 flex flex-col items-center bg-white p-10 rounded-lg shadow-lg max-w-md w-full transition-transform transform">
        
        
        <h2 className="text-4xl font-bold text-purple-700 mb-4">
          {isLogin ? "Entrar" : "Criar Conta"}
        </h2>
        <p className="text-gray-600 mb-8 text-center">
          {isLogin
            ? "Bem-vindo de volta! Entre para continuar."
            : "Crie uma conta para começar a apoiar causas!"}
        </p>

        
        <form onSubmit={handleSubmit} className="flex flex-col space-y-4 w-full">
          {!isLogin && (
            <input
              value={formData.name}
              onChange={handleChange}
              name="name"
              type="text"
              placeholder="Nome Completo"
              className="border-2 border-purple-700 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-purple-500 transition-colors"
            />
          )}
          <input
            value={formData.email}
            onChange={handleChange}
            name="email"
            type="email"
            placeholder="Email"
            className="border-2 border-purple-700 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-purple-500 transition-colors"
          />
          <input
            value={formData.password}
            onChange={handleChange}
            name="password"
            type="password"
            placeholder="Senha"
            className="border-2 border-purple-700 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-purple-500 transition-colors"
          />

          <button
            type="submit"
            className="bg-purple-700 text-white font-bold py-3 px-6 rounded-lg shadow-md hover:bg-purple-600 transition-colors"
          >
            {isLogin ? "Entrar" : "Criar Conta"}
          </button>
        </form>

        
        <button
          onClick={toggleForm}
          className="mt-6 text-purple-700 hover:text-purple-500 transition-colors"
        >
          {isLogin ? "Não tem uma conta? Cadastre-se" : "Já tem uma conta? Entre"}
        </button>
      </section>
    </div>
  )}

export default Auth
