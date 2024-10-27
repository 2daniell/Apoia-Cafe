import { Coffee, People, ThumbUp } from "@mui/icons-material";
import Image from "next/image";
import DropdownMenu from "./components/menu/DropdownMenu";

export default function Home() {

  return (
    <main className="min-h-screen bg-gray-50">
      <section className="relative flex flex-col lg:flex-row items-center justify-center min-h-[70vh] bg-gradient-to-b from-purple-800 to-purple-600 text-white py-20 px-4 lg:px-0">
        
        <DropdownMenu />

        <div className="flex flex-col lg:flex-row items-center justify-center w-full max-w-5xl lg:space-x-16">
          <div className="flex flex-col items-center lg:items-start lg:text-left text-center mb-10 lg:mb-0">
            <h1 className="text-5xl md:text-8xl font-extrabold mb-6 w-full text-center">Apoia Cafe</h1>
            <span className="text-lg md:text-2xl mb-8 max-w-2xl mx-auto lg:text-left text-center">
              Crie sua própria causa e inspire os outros a apoiar você com um café virtual! Cada contribuição faz a diferença.
            </span>
            <button className="bg-white m-auto text-purple-700 font-bold py-3 px-8 rounded-lg hover:bg-purple-200 transition duration-300 shadow-lg">
              Criar Minha Causa
            </button>
          </div>
          
          <div className="flex justify-center">
            <Image src="/assets/coffee-cup.png" width={300} height={300} alt="Copo de café" />
          </div>
        </div>
      </section>

      <section className="flex flex-col items-center py-20 bg-gradient-to-b from-purple-100 to-white px-4">
        <h2 className="text-4xl md:text-5xl font-bold text-purple-700 mb-12 text-center">Como Funciona?</h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-12 w-full max-w-5xl">
          
          <div className="p-10 bg-white rounded-lg shadow-lg text-center transition-transform transform hover:scale-105 border-2 border-purple-200">
            <Coffee className="mr-2 mb-5 text-purple-700" fontSize="large" />
            <span className="text-2xl md:text-4xl font-semibold text-purple-700 flex items-center justify-center mb-4">
              Selecione a Causa      
            </span>
            <p className="mt-2 text-gray-600">
              Selecione alguém que você gostaria de apoiar. Pense em amigos, familiares ou colegas que poderiam se beneficiar de um gesto gentil. Cada contribuição não é apenas um café, mas uma forma de mostrar que você se importa com as pessoas ao seu redor e valoriza a amizade. Sua escolha pode fazer a diferença!
            </p>
          </div>

          <div className="p-10 bg-white rounded-lg shadow-lg text-center transition-transform transform hover:scale-105 border-2 border-purple-200">
            <People className="mr-2 mb-5 text-purple-700" fontSize="large" />
            <span className="text-2xl md:text-4xl font-semibold text-purple-700 flex items-center justify-center mb-4">
              Envie um Café
            </span>
            <p className="mt-2 text-gray-600">
              Cada contribuição é uma maneira simples de mostrar que você se importa. 
              Com cada café enviado, você não só oferece um momento de alegria, 
              mas também ajuda a criar um impacto positivo na vida do outro. 
              Junte-se a nós para espalhar sorrisos e calor humano!
            </p>
          </div>

          <div className="p-10 bg-white rounded-lg shadow-lg text-center transition-transform transform hover:scale-105 border-2 border-purple-200">
            <ThumbUp className="mr-2 mb-5 text-purple-700" fontSize="large" />
            <span className="text-2xl md:text-4xl font-semibold text-purple-700 flex items-center justify-center mb-4">
              Faça a Diferença
            </span>
            <p className="mt-2 text-gray-600">
              Ajude a espalhar amor e apoio! Cada gesto conta, e juntos podemos fazer a diferença na vida de quem precisamos. O poder de um simples café pode criar conexões significativas e transformar o dia de alguém. Não subestime o impacto que suas ações podem ter!
            </p>
          </div>

        </div>
      </section>

      <hr />

      <section className="flex flex-col items-center py-20 px-4">
        <h2 className="text-4xl md:text-5xl font-bold text-purple-700 mb-6 text-center">Convide Seus Amigos!</h2>
        <p className="text-lg md:text-xl mb-8 text-center max-w-md">
          Ajude seus amigos a conhecer o ApoiaCafe e espalhar a solidariedade! Digite o e-mail deles abaixo.
        </p>
        <div className="flex flex-col md:flex-row items-center space-y-4 md:space-y-0 md:space-x-2 w-full max-w-md">
          <input 
            type="email" 
            placeholder="Digite o e-mail do seu amigo" 
            className="border-2 border-purple-700 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-purple-500 transition-colors w-full"
          />
          <button className="bg-purple-700 text-white font-bold py-2 px-6 rounded-lg shadow-md hover:bg-purple-600 transition-colors w-full md:w-auto">
            Enviar Convite
          </button>
        </div>
      </section>

      <footer className="bg-purple-700 text-white text-center font-semibold py-4">
        <p>© 2024 ApoiaCafe. Todos os direitos reservados.</p>
      </footer>
    </main>
  );
}
