import './App.css';
import React from 'react';
import { BrowserRouter, Routes, Route} from 'react-router-dom';

import Board from './Board';

function App() {
  return (
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Board />} /> {/* 메인 페이지 변경 */}
      </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
