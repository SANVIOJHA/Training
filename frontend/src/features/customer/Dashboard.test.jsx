import { render, screen, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import CustomerDashboard from './Dashboard';
import api from '../../services/api';

jest.mock('../../services/api', () => ({
  get: jest.fn(),
}));

const renderDashboard = () => render(
  <MemoryRouter>
    <CustomerDashboard />
  </MemoryRouter>
);

beforeEach(() => {
  jest.clearAllMocks();
});

test('renders customer deliveries returned by the API', async () => {
  api.get.mockResolvedValue({
    data: [
      {
        id: 1,
        trackingNumber: 'SC-100',
        createdAt: '2026-05-12T10:00:00',
        status: 'PENDING_DISPATCH',
        senderName: 'Alice',
        receiverName: 'Bob',
        source: 'Chennai',
        destination: 'Bengaluru',
        price: 499,
      },
    ],
  });

  renderDashboard();

  expect(await screen.findByText('SC-100')).toBeInTheDocument();
  expect(screen.getByText('PENDING DISPATCH')).toBeInTheDocument();
  expect(screen.getByText('Alice')).toBeInTheDocument();
  expect(screen.getByText('Bengaluru')).toBeInTheDocument();
  expect(api.get).toHaveBeenCalledWith('/deliveries/my');
});

test('renders an empty state when the customer has no deliveries', async () => {
  api.get.mockResolvedValue({ data: [] });

  renderDashboard();

  expect(await screen.findByText('No Deliveries Yet')).toBeInTheDocument();
});

test('renders a stable error state when delivery loading fails', async () => {
  api.get.mockRejectedValue(new Error('network down'));

  renderDashboard();

  await waitFor(() => {
    expect(screen.getByText('Failed to load deliveries')).toBeInTheDocument();
  });
});
