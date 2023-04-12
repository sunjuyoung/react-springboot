import "./App.css";
import { createBrowserRouter, Outlet, RouterProvider } from "react-router-dom";
import Navbar from "./components/navbar/Navbar";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Home from "./pages/home";
import { defaultFormat } from "moment/moment";
import ListingForm from "./pages/listing/ListingForm";

function App() {
  const Layout = () => {
    return (
      <div className="app">
        <Navbar />
        <Outlet />
      </div>
    );
  };

  const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      children: [
        {
          path: "/",
          element: <Home />,
        },
        {
          path: "/register",
          element: <Register />,
        },
        {
          path: "/login",
          element: <Login />,
        },
        {
          path: "/listingForm",
          element: <ListingForm />,
        },
      ],
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
