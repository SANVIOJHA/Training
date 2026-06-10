import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router-dom';
import Login from './Login';
import api from '../../services/api';
import { useAuth } from '../../context/AuthContext';

const mockNavigate = jest.fn();
const mockLogin = jest.fn();

jest.mock('../../services/api', () => ({
  post: jest.fn(),
}));

jest.mock('../../context/AuthContext', () => ({
  useAuth: jest.fn(),
}));

jest.mock('react-router-dom', () => {
  const actual = jest.requireActual('react-router-dom');
  return {
    ...actual,
    useNavigate: () => mockNavigate,
  };
});

const renderLogin = () => {
  useAuth.mockReturnValue({ login: mockLogin });
  return render(
    <MemoryRouter>
      <Login />
    </MemoryRouter>
  );
};

beforeEach(() => {
  jest.clearAllMocks();
  localStorage.clear();
});

test('submits credentials, stores token, and routes admins to the admin dashboard', async () => {
  const user = userEvent.setup();
  api.post.mockResolvedValue({
    data: {
      accessToken: 'access-token',
      refreshToken: 'refresh-token',
      username: 'admin',
      role: 'ADMIN',
    },
  });

  renderLogin();

  await user.type(screen.getByPlaceholderText(/enter your username/i), 'admin');
  await user.type(screen.getByPlaceholderText(/enter your password/i), 'Secret1!');
  await user.click(screen.getByRole('button', { name: /sign in/i }));

  await waitFor(() => {
    expect(api.post).toHaveBeenCalledWith('/auth/login', {
      username: 'admin',
      password: 'Secret1!',
    });
  });
  expect(localStorage.getItem('token')).toBe('access-token');
  expect(localStorage.getItem('refreshToken')).toBe('refresh-token');
  expect(mockLogin).toHaveBeenCalledWith({ username: 'admin', role: 'ADMIN' }, 'access-token');
  expect(mockNavigate).toHaveBeenCalledWith('/admin/dashboard');
});

test('shows backend login errors without navigating', async () => {
  const user = userEvent.setup();
  api.post.mockRejectedValue({
    response: {
      data: { message: 'Invalid credentials' },
    },
  });

  renderLogin();

  await user.type(screen.getByPlaceholderText(/enter your username/i), 'alice');
  await user.type(screen.getByPlaceholderText(/enter your password/i), 'wrong');
  await user.click(screen.getByRole('button', { name: /sign in/i }));

  expect(await screen.findByText('Invalid credentials')).toBeInTheDocument();
  expect(mockNavigate).not.toHaveBeenCalled();
});
