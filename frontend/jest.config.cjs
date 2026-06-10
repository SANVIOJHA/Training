module.exports = {
  testEnvironment: 'jsdom',
  setupFilesAfterEnv: ['<rootDir>/src/test/setupTests.cjs'],
  moduleNameMapper: {
    '\\.(css|less|scss|sass)$': 'identity-obj-proxy',
    '\\.(png|jpg|jpeg|gif|webp|svg|mp4)$': '<rootDir>/src/test/fileMock.cjs',
  },
  transform: {
    '^.+\\.[jt]sx?$': 'babel-jest',
  },
};
