import { ChangeEvent, useState, FocusEvent } from "react";

type usePasswordReturnType = {
  password: string;
  isValid: (password: string) => boolean;
  maxLength: number;
  invalidLengthMessage: string;
  handleChange: (e: ChangeEvent<HTMLInputElement>) => void;
  handleBlur: (e: FocusEvent<HTMLInputElement>) => void;
};

export const usePassword = (): usePasswordReturnType => {
  //
  const [password, setPassword] = useState<string>("");

  const isValid = (password: string): boolean => {
    return minLength <= password.length && password.length <= maxLength;
  };

  const minLength: number = 4;
  const maxLength: number = 128;

  const [invalidLengthMessage, setInvalidLengthMessage] = useState<string>("");

  const handleChange = (e: ChangeEvent<HTMLInputElement>): void => {
    setPassword(e.target.value);
  };

  const handleBlur = (e: FocusEvent<HTMLInputElement>): void => {
    //
    const value: string = e.target.value;

    if (!isValid(value)) {
      setInvalidLengthMessage(
        `パスワードは${minLength}文字以上${maxLength}文字以下で入力してください。`
      );
    } else {
      setInvalidLengthMessage("");
    }
  };

  return {
    password,
    isValid,
    maxLength,
    invalidLengthMessage,
    handleChange,
    handleBlur,
  };
};
